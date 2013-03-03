package com.nhimeye.security;

import java.util.ArrayList;
import java.util.List;

import com.nhimeye.data.domain.PermissionSection;

/**
 * Schema fragment(s) for this class:
 * 
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.nhimeye.com/securityconfig" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="SecurityConfig">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="ns:PermissionSection" name="pemissionSection" maxOccurs="unbounded"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
public class SecurityConfig {
    private List<PermissionSection> pemissionSections = new ArrayList<PermissionSection>();

    /**
     * Get the list of 'pemissionSection' element items.
     * 
     * @return list
     */
    public List<PermissionSection> getPemissionSections() {
        return pemissionSections;
    }

    /**
     * Set the list of 'pemissionSection' element items.
     * 
     * @param list
     */
    public void setPemissionSections(List<PermissionSection> list) {
        pemissionSections = list;
    }
}
