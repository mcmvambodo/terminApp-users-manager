package com.doitwell.group.springTesting.RDV;

import com.doitwell.group.springTesting.User.UserDTO;
import com.doitwell.group.springTesting.User.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/rdv")
public class RDVController {

    @Autowired
    private UserDetailsService userDetailsServiceervice;
    @Autowired
    private RDVService rdvService;




}
