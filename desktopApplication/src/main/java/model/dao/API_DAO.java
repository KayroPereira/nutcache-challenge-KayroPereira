package model.dao;

import lombok.Data;
import model.dao.service.ServiceApiRest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import exception.ExceptionAPICommunicationError;
import exception.ExceptionFailureReportApiRest;

@Data
public class API_DAO {
    private final ServiceApiRest serviceApiRest = new ServiceApiRest();
    private String error;

    public <T> String create(String url, T objectGeneric) throws ExceptionFailureReportApiRest {

        String responseCreate = "";

        try {
            String responsePost = serviceApiRest.postRequest(url, serviceApiRest.mapperEntityToJson(objectGeneric));
            responseCreate = serviceApiRest.requestError(responsePost);
        } catch (ExceptionAPICommunicationError | IOException exceptionAPICommunicationError) {
            setError(exceptionAPICommunicationError.toString());
            throw new ExceptionFailureReportApiRest(getError());
        }
        return responseCreate;
    }

    public <T> String put(String url, T objectGeneric, Integer id) throws ExceptionFailureReportApiRest {

        String responsePut = "";

        try {
            String responseRequestPut = serviceApiRest.putRequest(url, serviceApiRest.mapperEntityToJson(objectGeneric), id.toString());
            responsePut = serviceApiRest.requestError(responseRequestPut);
        } catch (ExceptionAPICommunicationError | IOException exceptionAPICommunicationError) {
            setError(exceptionAPICommunicationError.toString());
            throw new ExceptionFailureReportApiRest(getError());
        }
        return responsePut;
    }

    public <T> String patch(String url, T objectGeneric, Integer id) throws ExceptionFailureReportApiRest {
        String responsePatch = "";

        try {
            String responseRequestPatch = serviceApiRest.patchRequest(url, serviceApiRest.mapperEntityToJson(objectGeneric), id.toString());
            responsePatch = serviceApiRest.requestError(responseRequestPatch);
        } catch (ExceptionAPICommunicationError | IOException exceptionAPICommunicationError) {
            setError(exceptionAPICommunicationError.toString());
            throw new ExceptionFailureReportApiRest(getError());
        }
        return responsePatch;
    }

    public <T> List<T> findAll(String url, Class<T> entity) throws ExceptionFailureReportApiRest {

        List<T> listGenerics;
        String response ="";

        try {
            response = serviceApiRest.find(url, "");
            listGenerics = serviceApiRest.mapperObjectEntityList(response, entity);
        } catch (ExceptionAPICommunicationError exceptionAPICommunicationError) {
            try {
                setError(serviceApiRest.requestError(response));
            } catch (ExceptionAPICommunicationError apiCommunicationError) {
                setError("Unknown erros");
            } finally {
                throw new ExceptionFailureReportApiRest(getError());
            }
        }
        return listGenerics;
    }

    public <T> T findById(String url, Integer id, Class<T> entity) throws ExceptionFailureReportApiRest {

        T entityGeneric = null;
        String response ="";

        try {
            response = serviceApiRest.find(url, id.toString());
            entityGeneric = serviceApiRest.mapperObjectEntity(response, entity);
        } catch (ExceptionAPICommunicationError exceptionAPICommunicationError) {
            try {
                setError(serviceApiRest.requestError(response));
            } catch (ExceptionAPICommunicationError apiCommunicationError) {
                setError("Unknown erros");
            } finally {
                throw new ExceptionFailureReportApiRest(getError());
            }
        }
        return entityGeneric;
    }

    public <T> String delete(String url, Integer id) throws ExceptionFailureReportApiRest {

        String responseDelete = "";

        try {
            String responseRequestDelete = serviceApiRest.deleteRequest(url, id.toString());
            responseDelete = serviceApiRest.requestError(responseRequestDelete);
        } catch (ExceptionAPICommunicationError exceptionAPICommunicationError) {
            setError(exceptionAPICommunicationError.toString());
            throw new ExceptionFailureReportApiRest(getError());
        }
        return responseDelete;
    }
}