package com.sekoding.example.demo.services.impl;

import com.sekoding.example.demo.model.entity.Status;
import com.sekoding.example.demo.model.request.StatusRequest;
import com.sekoding.example.demo.model.response.FailedResponse;
import com.sekoding.example.demo.model.response.SuccessResponse;
import com.sekoding.example.demo.model.response.payload.StatusResponse;
import com.sekoding.example.demo.repository.StatusRepository;
import com.sekoding.example.demo.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StatusServiceImpl implements StatusService {

    @Autowired
    StatusRepository statusRepository;

    @Override
    public Object getStatuses() {
        try {
            List<Status> statusList = statusRepository.findAll();
            List<StatusResponse> statusResponseList = new ArrayList<>();
            for (Status status : statusList) {
                StatusResponse statusResponse = getStatusResponse(status);
                statusResponseList.add(statusResponse);
            }
            return new SuccessResponse(HttpStatus.OK, "Success", statusResponseList);
        } catch (Exception e) {
            return new FailedResponse(HttpStatus.MULTI_STATUS, e.getMessage());
        }
    }

    @Override
    public Object getStatus(Long userId) {
        try {
            Status status = statusRepository.findByUserId(userId);
            StatusResponse statusResponse = getStatusResponse(status);
            return new SuccessResponse(HttpStatus.OK, "Success", statusResponse);
        } catch (Exception e) {
            return new FailedResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public Object getStatusUser(Long no) {
        try {
            Boolean statusUser = null;
            if (no == 1) {
                statusUser = Boolean.TRUE;
            } else if (no == 0) {
                statusUser = Boolean.FALSE;
            }
            List<Status> statusList = statusRepository.findByUserStatus(statusUser);
            List<StatusResponse> statusResponseList = new ArrayList<>();
            for (Status status : statusList) {
                StatusResponse statusResponse = getStatusResponse(status);
                statusResponseList.add(statusResponse);
            }
            return new SuccessResponse(HttpStatus.OK, "Success", statusResponseList);
        } catch (Exception e) {
            return new FailedResponse(HttpStatus.MULTI_STATUS, e.getMessage());
        }
    }

    @Override
    public Object getSumUser(Long no) {
        try {
            Boolean statusUser = null;
            if (no == 1) {
                statusUser = Boolean.TRUE;
            } else if (no == 0) {
                statusUser = Boolean.FALSE;
            }
            List<Status> statusList = statusRepository.findByUserStatus(statusUser);
            Integer result = statusList.size();
            return new SuccessResponse(HttpStatus.OK, "Success", result);
        } catch (Exception e) {
            return new FailedResponse(HttpStatus.MULTI_STATUS, e.getMessage());
        }
    }

    @Override
    public Object createStatus(StatusRequest statusRequest) {
        try {
            Status status = new Status(
                statusRequest.getUser_id(),
                statusRequest.getNik(),
                statusRequest.getNama(),
                statusRequest.getStatus()
            );
            Status save = statusRepository.save(status);
            StatusResponse statusResponse = getStatusResponse(save);
            return new SuccessResponse(HttpStatus.OK, "Created", statusResponse);
        } catch (Exception e) {
            return new FailedResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public Object updateStatus(Long userId, StatusRequest statusRequest) {
        try {
            Status status = statusRepository.findByUserId(userId);
            status.setUserId(statusRequest.getUser_id());
            status.setUserStatus(statusRequest.getStatus());
            Status save = statusRepository.save(status);
            StatusResponse statusResponse = getStatusResponse(save);
            return new SuccessResponse(HttpStatus.OK, "Updated", statusResponse);
        } catch (Exception e) {
            return new FailedResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public Object deleteStatus(Long userId) {
        try {
            Status status = statusRepository.findByUserId(userId);
            StatusResponse statusResponse = getStatusResponse(status);
            statusRepository.deleteByUserId(userId);
            return new SuccessResponse(HttpStatus.OK, "Deleted", statusResponse);
        } catch (Exception e) {
            return new FailedResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public Object deleteStatuses() {
        try {
            List<Status> statusList = statusRepository.findAll();
            List<StatusResponse> statusResponseList = new ArrayList<>();
            for (Status status : statusList) {
                StatusResponse statusResponse = getStatusResponse(status);
                statusResponseList.add(statusResponse);
            }
            statusRepository.deleteAll();
            return new SuccessResponse(HttpStatus.OK, "Deleted", statusResponseList);
        } catch (Exception e) {
            return new FailedResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    //HELPER
    public StatusResponse getStatusResponse(Status status) {
        StatusResponse statusResponse = new StatusResponse(
            status.getUserId(),
            status.getNik(),
            status.getNama(),
            status.getUserStatus()
        );
        return statusResponse;
    }
}
