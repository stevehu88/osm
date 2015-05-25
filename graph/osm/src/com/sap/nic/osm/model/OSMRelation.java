package com.sap.nic.osm.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author  steve.hu@sap.com
 * @version created at：May 22, 2015 3:33:36 PM
 * 
 */
@XmlRootElement
public class OSMRelation extends OSMEntity {
	
	private ArrayList<Member> members = new ArrayList<>();
	
	public ArrayList<Member> getMembers() {
		return members;
	}
	
	public void setMembers(ArrayList<Member> members) {
		this.members = members;
	}
	
	public OSMEntity getMember(String role) {
		for (int i =0; i < members.size(); i++) {
			if (members.get(i).getRole().equals(role)) {
				return members.get(i).getMember();
			}
		}
		return null;
	}
	
	public void addMember(OSMEntity member, String role) {
		members.add(new Member(member, role));
	}
	
	public String getRelationType(String key) {
		return this.getValue(key);
	}

    public String toString() {
        String s = ("Relation-ID: " + this.getId() + " Relation-Type: " + this.getRelationType("type") +"\n");
        for (int i = 0; i < members.size(); i++) {
            s += ("Member: " + members.get(i).getRole() + ", ref:" + members.get(i).getId() + ", type:" + members.get(i).getType().getClass().getName() );
        }
        return s;
    }
    


	class Member {
		
		private OSMEntity member;
		private String role;
		
		public Member(OSMEntity member, String role) {
			this.member = member;
			this.role = role;
		}
		
		public Class<? extends OSMEntity> getType() {
			return member.getClass();
		}

		public OSMEntity getMember() {
			return member;
		}

		public void setMember(OSMEntity member) {
			this.member = member;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}
		
		public Long getId() {
			return member.getId();
		}
		
		
	}
	
}
