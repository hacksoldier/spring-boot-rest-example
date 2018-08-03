package com.khoubyari.example.api.rest;

import com.khoubyari.example.api.rest.dtos.UserDTO;
import com.khoubyari.example.domain.User;
import com.khoubyari.example.exception.DataFormatException;
import com.khoubyari.example.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
@RequestMapping(value = "/drugs/v1/users")
@Api(tags = {"User"})
public class UserController extends AbstractRestHandler {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a user resource.", notes = "Returns the URL of the new resource in the Location header.")
    public void createUser(@RequestBody @Valid UserDTO userDTO,
                           HttpServletRequest request, HttpServletResponse response) {
        User createdUser = this.userService.createUser(userDTO);
        response.setHeader("Location", request.getRequestURL().append("/").append(createdUser.getId()).toString());
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all users.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    @ResponseBody
    public Page<User> getAllHotel(@ApiParam(value = "The page number (zero-based)", required = true)
                                  @RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                  @ApiParam(value = "Tha page size", required = true)
                                  @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) Integer size,
                                  HttpServletRequest request, HttpServletResponse response) {
        return this.userService.getAllUser(page, size);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a single user.", notes = "You have to provide a valid user ID.")
    @ResponseBody
    public User getHotel(@ApiParam(value = "The ID of the hotel.", required = true)
                         @PathVariable("id") Long id,
                         HttpServletRequest request, HttpServletResponse response) {
        User user = this.userService.getUser(id);
        checkResourceFound(user);
        //todo: http://goo.gl/6iNAkz
        return user;
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update a user resource.", notes = "You have to provide a valid user ID in the URL and in the payload. The ID attribute can not be updated.")
    public void updateHotel(@ApiParam(value = "The ID of the existing hotel resource.", required = true)
                            @PathVariable("id") Long id, @RequestBody User user,
                            HttpServletRequest request, HttpServletResponse response) {
        checkResourceFound(this.userService.getUser(id));
        if (!id.equals(user.getId())) throw new DataFormatException("ID doesn't match!");
        this.userService.updateHotel(user);
    }

    //TODO: @ApiImplicitParams, @ApiResponses
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a user resource.", notes = "You have to provide a valid hotel ID in the URL. Once deleted the resource can not be recovered.")
    public void deleteHotel(@ApiParam(value = "The ID of the existing hotel resource.", required = true)
                            @PathVariable("id") Long id, HttpServletRequest request,
                            HttpServletResponse response) {
        checkResourceFound(this.userService.getUser(id));
        this.userService.deleteUser(id);
    }
}
