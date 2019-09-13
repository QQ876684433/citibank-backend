package com.example.ffbackend.controller;

import com.example.ffbackend.vo.RegularAdjustmentIndexVo;
import com.example.ffbackend.vo.ResponseBean;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.ffbackend.stubs.RegularAdjustmentIndex.UserIndex;

@RestController
@RequestMapping("/user")
public class RegularAdjustmentIndexController {

    @GetMapping(value = "/{user-id}/regular-adjustment-index")
    public ResponseBean<List<RegularAdjustmentIndexVo>> getRegularAdjustmentIndex(@PathVariable("user-id") Integer userId) {
        return new ResponseBean<>(true, UserIndex );
    }

//    @PutMapping(value = "/{user-id}/regular-adjustment-index")
//    public ResponseBean<Object> putRegularAdjustmentIndex(@PathVariable("user-id") String userId,
//            @RequestBody RegularAdjustmentIndexVo vo) {
//        return new ResponseBean<>(true, (Object) null);
//    }
}

