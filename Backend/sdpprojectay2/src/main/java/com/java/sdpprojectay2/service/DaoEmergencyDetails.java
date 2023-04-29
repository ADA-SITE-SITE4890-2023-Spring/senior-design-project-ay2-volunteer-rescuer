package com.java.sdpprojectay2.service;

import com.java.sdpprojectay2.config.MapConfig;
import com.java.sdpprojectay2.dto.ModelFileUrls;
import com.java.sdpprojectay2.dto.Response;
import com.java.sdpprojectay2.enums.Status;
import com.java.sdpprojectay2.exceptions.NotFoundException;
import com.java.sdpprojectay2.model.EmergencyDetails;
import com.java.sdpprojectay2.model.EmergencyDetailsFiles;
import com.java.sdpprojectay2.model.Users;
import com.java.sdpprojectay2.repository.RepoEmergencyDetails;
import com.java.sdpprojectay2.repository.RepoEmergencyDetailsFiles;
import com.java.sdpprojectay2.repository.RepoUsers;
import com.google.maps.GeoApiContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


@Component
public class DaoEmergencyDetails {

    @Autowired
    RepoEmergencyDetails repoEmergencyDetails;
    @Autowired
    DaoMedia daoMedia;
    @Autowired
    RepoEmergencyDetailsFiles repoEmergencyDetailsFiles;
    @Autowired
    RepoUsers repoUsers;
    @Autowired
    DaoUsers daoUsers;

    private final GeoApiContext geoApiContext;

    private final MapConfig m;

    public DaoEmergencyDetails(GeoApiContext geoApiContext, MapConfig m) {
        this.geoApiContext = geoApiContext;
        this.m = m;
    }

    public Response insert(String latitude, String longitude, String description, MultipartFile[] multipartFileList, MultipartFile voice, long idUser, HttpServletRequest httpServletRequest){
        EmergencyDetails emergencyDetails = new EmergencyDetails();
        Users user = daoUsers.getCustomerById(idUser);
        if(user == null) {
            throw new NotFoundException();
        }
        String url = "https://www.google.com/maps/embed/v1/place?q=" +
                latitude + "," + longitude + "&key=" +
                m.getApiKey() +
                "&zoom=15";
        repoEmergencyDetails.save(emergencyDetails
                .setLatitude(Double.valueOf(latitude))
                .setLongitude(Double.valueOf(longitude))
                .setLocation(url)
                .setDescription(description)
                .setVoiceRecord(daoMedia.localInsert(voice, httpServletRequest).getRemotePath())
                .setMediaFiles(daoMedia.localInsertListFiles(multipartFileList, httpServletRequest))
                .setCreatedDate(LocalDateTime.now())
                .setDeleted(false)
                .setStatus(Status.WAITING.getStatus())
                .setUser(user));

        for (ModelFileUrls modelFileUrls: emergencyDetails.getMediaFiles()) {
            repoEmergencyDetailsFiles.save(new EmergencyDetailsFiles()
                    .setIdEmergencyDetails(emergencyDetails.getId())
                    .setUrl(modelFileUrls.getUrl())
                    .setIdMedia(modelFileUrls.getIdMedia()));
        }

        return new Response();
    }



}
