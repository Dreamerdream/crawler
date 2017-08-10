package cn.wanghaomiao.seimi.mapper;

import cn.wanghaomiao.seimi.model.UserAgent;
import cn.wanghaomiao.seimi.model.UserAgentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAgentMapper {
    long countByExample(UserAgentExample example);

    int deleteByExample(UserAgentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserAgent record);

    int insertSelective(UserAgent record);

    List<UserAgent> selectByExample(UserAgentExample example);

    UserAgent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserAgent record, @Param("example") UserAgentExample example);

    int updateByExample(@Param("record") UserAgent record, @Param("example") UserAgentExample example);

    int updateByPrimaryKeySelective(UserAgent record);

    int updateByPrimaryKey(UserAgent record);
}