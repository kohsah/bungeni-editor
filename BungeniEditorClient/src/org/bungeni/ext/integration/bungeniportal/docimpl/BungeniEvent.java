/*
 * Copyright (C) 2013 UN/DESA Africa i-Parliaments
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.bungeni.ext.integration.bungeniportal.docimpl;

import java.util.List;

/**
 *
 * @author Ashok Hariharan
 */
public class BungeniEvent extends BungeniBaseDoc {
    private Integer head_id;
    private List<BungeniAtt> attachments;

    /**
     * @return the head_id
     */
    public Integer getHead_id() {
        return head_id;
    }

    /**
     * @return the attachments
     */
    public List<BungeniAtt> getAttachments() {
        return attachments;
    }

}
