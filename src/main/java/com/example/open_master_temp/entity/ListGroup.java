package com.example.open_master_temp.entity;

import java.util.List;
import lombok.Value;

@Value
public class ListGroup {

    List<TransferObject> list;

    public ListGroup(final List<TransferObject> allByIdxAfter) {
        this.list = allByIdxAfter;
    }

    public long getNextIdx() {
        if (list.isEmpty()) {
            return 0;
        }
        int lastIdx = list.size() - 1;
        TransferObject lastElement = list.get(lastIdx);

        return lastElement.getIdx() + 1;
    }
}
