package com.taotao.manage.pojo;

import java.util.Date;

/**
 * 将相同的字段放到通用pojo中
 * @author libanglong
 *
 */
public abstract class BasePojo {
    
    private Date created;
    private Date updated;
    
    
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public Date getUpdated() {
        return updated;
    }
    public void setUpdated(Date updated) {
        this.updated = updated;
    }
    
    

}
