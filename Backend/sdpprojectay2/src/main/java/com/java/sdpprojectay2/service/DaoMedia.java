package com.java.sdpprojectay2.service;

import com.java.sdpprojectay2.config.MediaStorageProperties;
import com.java.sdpprojectay2.dto.ModelFileUrls;
import com.java.sdpprojectay2.dto.Response;
import com.java.sdpprojectay2.exceptions.InternalServerErrorException;
import com.java.sdpprojectay2.exceptions.MissingParameterException;
import com.java.sdpprojectay2.exceptions.NotFoundException;
import com.java.sdpprojectay2.model.Media;
import com.java.sdpprojectay2.repository.RepoMedia;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Component
public class DaoMedia {

    @Autowired
    RepoMedia repoMedia;

    private Path fileStorageLocation;

    @Autowired
    public DaoMedia(MediaStorageProperties mediaStorageProperties) {
        this.fileStorageLocation = Paths.get(mediaStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new InternalServerErrorException();
        }
    }

    private Path getFileStorageLocation(String year, String month, String day) {
        Path pathOfYear = Paths.get(this.fileStorageLocation.toAbsolutePath().toString() + "/" + year).toAbsolutePath().normalize();
        Path pathOfMonth = Paths.get(this.fileStorageLocation.toAbsolutePath().toString() + "/" + year + "/" + month).toAbsolutePath().normalize();
        Path pathOfDay = Paths.get(this.fileStorageLocation.toAbsolutePath().toString() + "/" + year + "/" + month + "/" + day).toAbsolutePath().normalize();
        try {
            Files.createDirectories(pathOfYear);
            Files.createDirectories(pathOfMonth);
            Files.createDirectories(pathOfDay);
            return pathOfDay;
        } catch (Exception ex) {
            throw new InternalServerErrorException();
        }
    }

    public Response insert(MultipartFile file, HttpServletRequest request) {
        Media media = localInsert(file, request);
        return new Response().setResponse(media);
    }

    public Media localSelectByRemotePath(String remotePath) {
        return repoMedia.findByRemotePath(remotePath).orElse(null);
    }

    public Media localInsert(MultipartFile file, HttpServletRequest request) {
        if (file != null && file.getSize() > 0 && file.getOriginalFilename() != null) {
            String remoteName = StringUtils.cleanPath(file.getOriginalFilename());
            try {
                if (remoteName.contains("..")) {
                    throw new MissingParameterException();
                } else {
                    String year = new SimpleDateFormat("yyyy").format(new Date());
                    String month = new SimpleDateFormat("MM").format(new Date());
                    String day = new SimpleDateFormat("dd").format(new Date());

                    getFileStorageLocation(year, month, day);

                    String localName = UUID.randomUUID().toString().replaceAll("-", "");
                    String localPath = year + "/" + month + "/" + day + "/" + localName + "." + getExtension(remoteName);
                    Path targetLocation = fileStorageLocation.resolve(localPath);

                    if (Files.exists(targetLocation)) {
                        localName = UUID.randomUUID().toString().replaceAll("-", "") + getCurrentMillis();
                        localPath = year + "/" + month + "/" + day + "/" + localName + "." + getExtension(remoteName);
                        targetLocation = fileStorageLocation.resolve(localPath);
                    }

                    Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

                    String remotePath = getUrl(request) + "/volunteer-rescue-project/media/download/" + localPath;

                    Media media=new Media();
                    repoMedia.save(media
                            .setRemoteName(remoteName)
                            .setLocalName(localName)
                            .setFileType(file.getContentType())
                            .setLocalPath(localPath)
                            .setRemotePath(remotePath)
                            .setFileSize(file.getSize())
                            .setInsertDate(LocalDateTime.now())
                            .setDeleted(false)
                    );
//                    System.out.println("media-"+media);
                    return media;
                }
            } catch (IOException ex) {
                throw new InternalServerErrorException();
            }
        } else {
            throw new InternalServerErrorException();
        }
    }


    public List<ModelFileUrls> localInsertListFiles(MultipartFile[] files, HttpServletRequest request) {
        List<ModelFileUrls> fileUrls = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file != null && file.getSize() > 0 && file.getOriginalFilename() != null) {
                String remoteName = StringUtils.cleanPath(file.getOriginalFilename());
                try {
                    if (remoteName.contains("..")) {
                        throw new MissingParameterException();
                    } else {
                        String year = new SimpleDateFormat("yyyy").format(new Date());
                        String month = new SimpleDateFormat("MM").format(new Date());
                        String day = new SimpleDateFormat("dd").format(new Date());

                        getFileStorageLocation(year, month, day);

                        String localName = UUID.randomUUID().toString().replaceAll("-", "");
                        String localPath = year + "/" + month + "/" + day + "/" + localName + "." + getExtension(remoteName);
                        Path targetLocation = fileStorageLocation.resolve(localPath);

                        if (Files.exists(targetLocation)) {
                            localName = UUID.randomUUID().toString().replaceAll("-", "") + getCurrentMillis();
                            localPath = year + "/" + month + "/" + day + "/" + localName + "." + getExtension(remoteName);
                            targetLocation = fileStorageLocation.resolve(localPath);
                        }

                        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

                        String remotePath = getUrl(request) + "/volunteer-rescue-project/media/download/" + localPath;

                        Media media=new Media();
                        repoMedia.save(media
                                .setRemoteName(remoteName)
                                .setLocalName(localName)
                                .setFileType(file.getContentType())
                                .setLocalPath(localPath)
                                .setRemotePath(remotePath)
                                .setFileSize(file.getSize())
                                .setInsertDate(LocalDateTime.now())
                                .setDeleted(false)
                        );
                        fileUrls.add(new ModelFileUrls().setUrl(remotePath).setIdMedia(media.getId()));
                    }
                } catch (IOException ex) {
                    throw new InternalServerErrorException();
                }
            } else {
                throw new InternalServerErrorException();
            }
        }
        return fileUrls;
    }


    public String getUrl(HttpServletRequest request) {
        HttpRequest httpRequest = new ServletServerHttpRequest(request);
        UriComponents uriComponents = UriComponentsBuilder.fromHttpRequest(httpRequest).build();

        String scheme = Objects.requireNonNull(uriComponents.getScheme());
        // .replace("http","https")        // http / https
        String serverName = request.getServerName();     // hostname.com
        int serverPort = request.getServerPort();        // 80
        String contextPath = request.getContextPath();   // /app

        // Reconstruct original requesting URL
        StringBuilder url = new StringBuilder();
        url.append(scheme).append("://");
        url.append(serverName);

        if (serverPort != 80 && serverPort != 443) {
            url.append(":").append(serverPort);
        } else {
            url.append(":");
        }
        url.append(contextPath);
        return url.toString();
    }

    private String getExtension(String filename) {
        return FilenameUtils.getExtension(filename);
    }

    public ResponseEntity<Resource> downloadFile(String year, String month, String day, String filename, HttpServletRequest request) {
        try {
            Path filePath = fileStorageLocation.resolve(year + "/" + month + "/" + day + "/" + filename).toAbsolutePath();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return getResponse(resource, filename, request);
            } else {
                throw new NotFoundException();
            }
        } catch (MalformedURLException ex) {
            throw new NotFoundException();
        }
    }

    ResponseEntity<Resource> getResponse(Resource resource, String filename, HttpServletRequest request) {
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ignored) {
        }
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(resource);
    }

    public long getCurrentMillis() {
        LocalDateTime ldt = LocalDateTime.now();
        return ldt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
