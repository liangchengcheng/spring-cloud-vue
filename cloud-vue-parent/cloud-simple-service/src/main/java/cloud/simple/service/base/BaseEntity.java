package cloud.simple.service.base;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by liangchengcheng on 2017/7/22.
 */
@Entity
public class BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Transient
    private Integer page = 1;

    @Transient
    private Integer rows = 10;

	/*@Column(name="enable_")
	private Integer enable;
	@Column(name="remark_")
	private String remark;
	private Long createBy;
	private Date createTime;
	private Long updateBy;
	private Date updateTime;*/


    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}

