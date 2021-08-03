package com.coding;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
/*카카오톡 오픈채팅방에서는 친구가 아닌 사람들과 대화를 할 수 있는데, 본래 닉네임이 아닌 가상의 닉네임을 사용하여 채팅방에 들어갈 수 있다.
 *  사람이 들어오고 나가는 관리창 만들기 
 *  
 *  record는 다음과 같은 문자열이 담긴 배열이며, 길이는 1 이상 100,000 이하이다.
다음은 record에 담긴 문자열에 대한 설명이다.
모든 유저는 [유저 아이디]로 구분한다.
[유저 아이디] 사용자가 [닉네임]으로 채팅방에 입장 - "Enter [유저 아이디] [닉네임]" (ex. "Enter uid1234 Muzi")
[유저 아이디] 사용자가 채팅방에서 퇴장 - "Leave [유저 아이디]" (ex. "Leave uid1234")
[유저 아이디] 사용자가 닉네임을 [닉네임]으로 변경 - "Change [유저 아이디] [닉네임]" (ex. "Change uid1234 Muzi")
첫 단어는 Enter, Leave, Change 중 하나이다.
각 단어는 공백으로 구분되어 있으며, 알파벳 대문자, 소문자, 숫자로만 이루어져있다.
유저 아이디와 닉네임은 알파벳 대문자, 소문자를 구별한다.
유저 아이디와 닉네임의 길이는 1 이상 10 이하이다.
채팅방에서 나간 유저가 닉네임을 변경하는 등 잘못 된 입력은 주어지지 않는다.
 *  
 *  1차 목표
 *       "[닉네임]님이 들어왔습니다."
         채팅방에서 누군가 나가면 다음 메시지가 출력된다.
        "[닉네임]님이 나갔습니다."
 */
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OpenChattingApplicationTests {
	String[] record = {"Enter uid1234 Muzi","Enter uid4567 Prodo","Leave uid1234"};
	Map<String,String> idMap = new HashMap<>();
	List<String> keydata = new ArrayList<>();

	@Test
	void 첫번째_record_데이터분리() {
		for(int i=0; i< record.length; i++) {
			System.out.println(record[i]);
		}
	}
	@Test
	void 첫번째배열_Enter_구분자확인하기() {
		String[] keyword = record[0].split(" ");
		assertThat(keyword[0]).isEqualTo("Enter");
	}
	@Test
	void 첫번째배열_아이디_구분자확인하기() {
		String[] keyword = record[0].split(" ");
		assertThat(keyword[1]).isEqualTo("uid1234");
	}
	
	@Test
	void 해쉬맵에아이디닉네임담은후_1번닉네임가져오기_uid1234_Muzi(){
		//1차 for문돌려 데이터 체크 
		splitIdAndNickname(record);
		 assertThat(idMap.get("uid1234")).isEqualTo("Muzi");
		
	}
	@Test
	void 해쉬맵에아이디닉네임담은후_2번닉네임가져오기_uid4567_Prodo_실패하기(){
		//1차 for문돌려 데이터 체크 
		splitIdAndNickname(record);
		 assertThat(idMap.get("uid4567")).isEqualTo("Muzi");
		
	}
	@Test
	void 해쉬맵에아이디닉네임담은후_2번닉네임가져오기_uid4567_Prodo(){
		//1차 for문돌려 데이터 체크 
		 splitIdAndNickname(record);
		 assertThat(idMap.get("uid4567")).isEqualTo("Prodo");
		
	}
	@Test
	void 닉네임으로들어왔습니다출력() {
		 splitIdAndNickname(record);
		 for(int i =0; i<idMap.size(); i++) {
			 System.out.println(idMap.get(keydata.get(i)) + "님이 들어왔습니다");
			 
		 }
		
	}
	
	private void splitIdAndNickname(String[] record){
		for(int i=0; i< record.length; i++) {
			String[] keyword = record[i].split(" "); //for문안에서 초기화를 시키면서  hashmap에 담기 
			    if(keyword[0].equals("Enter")) {
			    	keydata.add(keyword[1]);
			    	idMap.put(keyword[1], keyword[2]);
			    }
		}
	}
	

}
