package com.atguigu.service;

import com.atguigu.pojo.Headline;
import com.atguigu.pojo.vo.PortalVo;
import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 李文
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-03-19 19:36:32
*/
public interface HeadlineService extends IService<Headline> {

    Result findNewspage(PortalVo portalVo);

    Result showHeadlineDetail(Integer hid);

    Result publish(Headline headline,String token);

    Result updateData(Headline headline);

    Result Delete(Integer hid);
}
