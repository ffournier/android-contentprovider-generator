/*
 * This source is part of the
 *      _____  ___   ____
 *  __ / / _ \/ _ | / __/___  _______ _
 * / // / , _/ __ |/ _/_/ _ \/ __/ _ `/
 * \___/_/|_/_/ |_/_/ (_)___/_/  \_, /
 *                              /___/
 * repository.
 * 
 * Copyright (C) 2012-2014 Benoit 'BoD' Lubek (BoD@JRAF.org)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.jraf.androidcontentprovidergenerator.model;

import org.jraf.androidcontentprovidergenerator.Log;

/**
 * @author 
 *
 */
public class EnumConstructor {

	public String mName;
	public Field.Type mType;
	public String mTypeName;
	
	 public EnumConstructor(String name, Field.Type type){
        mName = name;
        mType = type;
        mTypeName = type.getJsonName();
    }

    public String getName() {
        return mName;
    }

    public Field.Type getType() {
        return mType;
    }
    
    public String getTypeName() {
        return mTypeName;
    }
    
    public boolean testValideValue(String value) {
    	if (value == null)
    		return true;
        switch (mType) {
        	case STRING:
        		// control if we have quote in start and end
        		if (value.startsWith("\"") && value.endsWith("\""))
        			return true;
            case BOOLEAN:
                if ("true".equals(value) || "false".equals(value)) 
                	return true;
                break;
            case INTEGER:
            	try {
            		Integer.parseInt(value);
                	return true;
                } catch (NumberFormatException e) {
                    Log.w("TAG", "The default value for field " + value + "." + getName()
                            + " could not be parsed as a numeric type, which is probably a problem", e);
                }
                break;
            case LONG:
            case DATE:
            case ENUM:
                try {
                    Long.parseLong(value);
                    return true;
                } catch (NumberFormatException e) {
                    Log.w("TAG", "The default value for field " + value + "." + getName()
                            + " could not be parsed as a numeric type, which is probably a problem", e);
                }
                break;
            case FLOAT:
            	try {
                    Float.parseFloat(value);
                    return true;
                } catch (NumberFormatException e) {
                    Log.w("TAG", "The default value for field " + value + "." + getName()
                            + " could not be parsed as a floating point type, which is probably a problem", e);
                }
            	break;
            case DOUBLE:
                try {
                    Double.parseDouble(value);
                    return true;
                } catch (NumberFormatException e) {
                    Log.w("TAG", "The default value for field " + value + "." + getName()
                            + " could not be parsed as a floating point type, which is probably a problem", e);
                }
                break;
            default:
                // fallthrough
                return false;
        }
        return false;
    }
}
