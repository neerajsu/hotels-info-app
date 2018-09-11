package com.expedia.assessment.hotelsinfoapp.web;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.expedia.assessment.hotelsinfoapp.constants.CommonConstants;
import com.expedia.assessment.hotelsinfoapp.constants.SortOptionEnum;
import com.expedia.assessment.hotelsinfoapp.exception.APIException;
import com.expedia.assessment.hotelsinfoapp.exception.ErrorInfo;
import com.expedia.assessment.hotelsinfoapp.exception.ServiceException;
import com.expedia.assessment.hotelsinfoapp.model.Hotel;
import com.expedia.assessment.hotelsinfoapp.service.HotelsInfoService;

@RestController
@RequestMapping(path = "/location")
public class HotelsInfoController {

	@Autowired
	HotelsInfoService hotelsInfoService;

	private static final String DOC_URL = "http://documentation-url";

	private static final Logger LOG = LogManager.getLogger(HotelsInfoController.class.getName());
	
	@GetMapping(value = "/ping")
    public String checkResourceHealth() {
        return "Application is up and running";
    }

	@RequestMapping(method = RequestMethod.GET, path = "/{locationId}/hotels", params = { "sort_by", "is_ascending" })
	public List<Hotel> getHotelsInfo(@PathVariable("locationId") int locationId,
			@RequestParam(value = "sort_by") final SortOptionEnum sortOption,
			@RequestParam(value = "is_ascending") final boolean isAscending) throws ServiceException {
		return hotelsInfoService.getHotelsNearLocation(locationId, sortOption, isAscending);
	}

	@ExceptionHandler(APIException.class)
	public ResponseEntity<ErrorInfo> exceptionHandlerForApiException(APIException exception) {

		LOG.error("APIException occured ::", exception);
		return new ResponseEntity<ErrorInfo>(new ErrorInfo(CommonConstants.FAIL_STATUS, exception.getMessage(),
				exception.getStatus().getReasonPhrase()), exception.getStatus());
	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class, MethodArgumentNotValidException.class })
	public ResponseEntity<ErrorInfo> exceptionHandlerForMethodArgumentTypeMismatchException(
			MethodArgumentTypeMismatchException exception) {
		LOG.error("MethodArgumentTypeMismatchException occured ::", exception);
		return new ResponseEntity<ErrorInfo>(new ErrorInfo(CommonConstants.FAIL_STATUS,
				"Unable to parse request. Please check your request and try again. You can also refer to documentation found at : "
						+ DOC_URL,
				HttpStatus.BAD_REQUEST.getReasonPhrase()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> exceptionHandler(Exception exception) {

		LOG.error("Exception occured ::", exception);
		return new ResponseEntity<ErrorInfo>(new ErrorInfo(CommonConstants.FAIL_STATUS, exception.getLocalizedMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
