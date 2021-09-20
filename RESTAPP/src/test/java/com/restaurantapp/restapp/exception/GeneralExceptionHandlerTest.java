package com.restaurantapp.restapp.exception;

import com.restaurantapp.restapp.model.entity.City;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

@RunWith(SpringRunner.class)
public class GeneralExceptionHandlerTest {

    private GeneralExceptionHandler generalExceptionHandler = new GeneralExceptionHandler();

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    @Mock
    private HttpHeaders headers;

    @Mock
    private WebRequest request;

    @Test
    public void userNotFoundExceptionHandler() {
        UserNotFoundException exception = new UserNotFoundException();
        ResponseEntity<?> responseEntity = generalExceptionHandler.userNotFoundExceptionHandler(exception);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void branchNotFounExceptionHandler() {
        BranchNotFoundException exception = new BranchNotFoundException();
        ResponseEntity<?> responseEntity = generalExceptionHandler.branchNotFounExceptionHandler(exception);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void mealNotFoundException() {
        MealNotFoundException exception = new MealNotFoundException();
        ResponseEntity<?> responseEntity = generalExceptionHandler.mealNotFoundException(exception);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void menuNotFoundException() {
        MenuNotFoundException exception = new MenuNotFoundException();
        ResponseEntity<?> responseEntity = generalExceptionHandler.menuNotFoundException(exception);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void addressNotFoundException() {
        AddressNotFoundException exception = new AddressNotFoundException();
        ResponseEntity<?> responseEntity = generalExceptionHandler.addressNotFoundException(exception);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void cityNotFoundException() {
        CityNotFoundException exception = new CityNotFoundException();
        ResponseEntity<?> responseEntity = generalExceptionHandler.cityNotFoundException(exception);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void commentNotFoundException() {
        CommentNotFoundException exception = new CommentNotFoundException();
        ResponseEntity<?> responseEntity = generalExceptionHandler.commentNotFoundException(exception);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void countyNotFoundException() {
        CountyNotFoundException exception = new CountyNotFoundException();
        ResponseEntity<?> responseEntity = generalExceptionHandler.countyNotFoundException(exception);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void invalidOwnerException() {
        InvalidOwnerException exception = new InvalidOwnerException();
        ResponseEntity<?> responseEntity = generalExceptionHandler.invalidOwnerException(exception);
        Assertions.assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void invalidOwnerException_message() {
        InvalidOwnerException exception = new InvalidOwnerException("Invalid owner!");
        ResponseEntity<?> responseEntity = generalExceptionHandler.invalidOwnerException(exception);
        Assertions.assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void invalidRoleException() {
        InvalidRoleException exception = new InvalidRoleException();
        ResponseEntity<?> responseEntity = generalExceptionHandler.invalidRoleException(exception);
        Assertions.assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }
    @Test
    public void invalidRoleException_message() {
        InvalidRoleException exception = new InvalidRoleException("Invalid user role");
        ResponseEntity<?> responseEntity = generalExceptionHandler.invalidRoleException(exception);
        Assertions.assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void restaurantNotFoundException() {
        RestaurantNotFoundException exception = new RestaurantNotFoundException();
        ResponseEntity<?> responseEntity = generalExceptionHandler.restaurantNotFoundException(exception);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}