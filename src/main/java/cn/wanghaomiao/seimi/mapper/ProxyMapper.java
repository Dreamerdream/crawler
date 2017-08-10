package cn.wanghaomiao.seimi.mapper;

import cn.wanghaomiao.seimi.model.Proxy;
import cn.wanghaomiao.seimi.model.ProxyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProxyMapper {
    long countByExample(ProxyExample example);

    int deleteByExample(ProxyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Proxy record);

    int insertSelective(Proxy record);

    List<Proxy> selectByExample(ProxyExample example);

    Proxy selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Proxy record, @Param("example") ProxyExample example);

    int updateByExample(@Param("record") Proxy record, @Param("example") ProxyExample example);

    int updateByPrimaryKeySelective(Proxy record);

    int updateByPrimaryKey(Proxy record);
}