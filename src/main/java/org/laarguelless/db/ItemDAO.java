package org.laarguelless.db;

import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

interface ItemDAO {

    @SqlUpdate("insert into items (id,title,categoryId,startTime,stopTime) values(:id,:title,:categoryId,:startTime,:stopTime)")
    void insert(@BindBean Item item);
}

