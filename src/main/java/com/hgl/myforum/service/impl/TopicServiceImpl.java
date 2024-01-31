package com.hgl.myforum.service.impl;

import com.hgl.myforum.entity.TTopic;
import com.hgl.myforum.entity.dto.TopicDto;
import com.hgl.myforum.mapper.TTopicMapper;
import com.hgl.myforum.service.ITopicService;
import com.hgl.myforum.utils.ExportUtil;
import com.hgl.myforum.utils.RedisClient;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author HGL
 * @Date 2020/9/2
 */
@Service
public class TopicServiceImpl implements ITopicService {

    @Autowired
    private TTopicMapper topicMapper;
    @Autowired
    private RedisClient redisClient;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public List<TopicDto> getTopicList() {
        return topicMapper.getTopicList();
    }

    public void exportExcel(HttpServletResponse xls) {
        //查询帖子列表
        List<TopicDto> topicList = topicMapper.getTopicList();
        //工作表表名
        String tableName = "帖子列表";
        //新建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        //新建工作表
        HSSFSheet sheet = wb.createSheet(tableName);
        //给工作簿设定样式
        CellStyle style = ExportUtil.getCellStyle(wb);
        //每个工作表中的列名
        String[] headers = {"标题", "内容", "时间", "发帖人"};
        ExportUtil.setAllHeader(wb, sheet, headers, style);

        //遍历帖子列表
        for (int i = 0; i < topicList.size(); i++) {
            TopicDto t = topicList.get(i);
            String[] cells = new String[]{
                    t.getTitle(), t.getContent(), sdf.format(t.getCreateTime()), t.getUserName()
            };
            ExportUtil.setAllCell(wb, sheet, i + 1, cells, style);
        }

        ExportUtil.exportXls(wb, tableName, xls);
    }


    /**
     * 定时任务（定时发帖）
     * 频率：每天12点到14点、17点到19点，每隔10分钟执行一次
     */
    @Override
    public void regularPosting() throws Exception {
        System.out.println("============开始发帖==============");
        System.out.println("发帖时间："+sdf.format(new Date()));
        Long count = redisClient.count("count");
        System.out.println("count缓存的值为：["+count+"]");
        TTopic tTopic = new TTopic();
        tTopic.setUserId(15);
        tTopic.setContent("就在刚刚，我发布了我的第"+count+"个帖");
        tTopic.setCreateTime(new Date());
        tTopic.setTitle("这是我第"+count+"个帖的标题");
        int insert = topicMapper.insert(tTopic);
        if (insert < 1){
            System.out.println("第"+count+"个发布失败了~~~");
        }else {
            System.out.println("第"+count+"个发布成功了~~~");
        }
    }
}
