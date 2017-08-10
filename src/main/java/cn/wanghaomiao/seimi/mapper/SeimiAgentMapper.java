package cn.wanghaomiao.seimi.mapper;

import cn.wanghaomiao.seimi.model.SeimiAgent;
import cn.wanghaomiao.seimi.model.SeimiAgentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SeimiAgentMapper {
    long countByExample(SeimiAgentExample example);

    int deleteByExample(SeimiAgentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SeimiAgent record);

    int insertSelective(SeimiAgent record);

    List<SeimiAgent> selectByExample(SeimiAgentExample example);

    SeimiAgent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SeimiAgent record, @Param("example") SeimiAgentExample example);

    int updateByExample(@Param("record") SeimiAgent record, @Param("example") SeimiAgentExample example);

    int updateByPrimaryKeySelective(SeimiAgent record);

    int updateByPrimaryKey(SeimiAgent record);
}