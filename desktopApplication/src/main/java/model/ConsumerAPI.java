package model;

import java.util.ArrayList;
import java.util.List;

import controller.entity.Person;
import exception.ExceptionFailureReportApiRest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.dao.API_DAO;

@Data
@Builder
@AllArgsConstructor
public class ConsumerAPI {

	private final API_DAO api_dao = new API_DAO();

	public <T> List<T> get(String url, Class<T> entityGeneric) {
		List<T> listEntityGeneric = new ArrayList<>();
		try {
			listEntityGeneric = api_dao.findAll(url, entityGeneric);
		} catch (ExceptionFailureReportApiRest exceptionFailureReportApiRest) {
			System.out.println(exceptionFailureReportApiRest);
		}
		return listEntityGeneric;
	}

	public <T> String create(String url, T genericPost) {
		String responsePost = "";
		try {
			responsePost = api_dao.create(url, genericPost);
			System.out.println(responsePost);
		} catch (ExceptionFailureReportApiRest exceptionFailureReportApiRest) {
			System.out.println(exceptionFailureReportApiRest);
		}
		return responsePost;
	}

	public <T> String update(String url, Long id, T genericPatch) {
		String responsePatch = "";
		try {
			responsePatch = api_dao.patch(url, genericPatch, id.intValue());
			System.out.println(responsePatch);
		} catch (ExceptionFailureReportApiRest exceptionFailureReportApiRest) {
			System.out.println(exceptionFailureReportApiRest);
		}
		return responsePatch;
	}

	public String delete(String url, Long id) {
		String responseDelete = "";
		try {
			responseDelete = api_dao.delete(url, id.intValue());
		} catch (ExceptionFailureReportApiRest exceptionFailureReportApiRest) {
			System.out.println(exceptionFailureReportApiRest);
		};
		return responseDelete;
	}
}
