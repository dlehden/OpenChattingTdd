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
class OpenChattingApplicationTests2 {
	String[] record = {"Enter uid1234 Muzi","Enter uid4567 Prodo"
			           ,"Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
	Map<String,String> idMap = new HashMap<>();
	List<String> keydata = new ArrayList<>();
	List<String> printdata = new ArrayList<>();

//	@Test
//	void 닉네임으로들어왔습니다출력() {
//		 splitIdAndNickname(record);
//	}
	@Test
	void 방나가고들어오는상태() {
		splitIdAndNickname(record);
		EnterLeavePrint();
		
	}
	
	
	private void splitIdAndNickname(String[] record){
		for(int i=0; i< record.length; i++) {
			String[] keyword = record[i].split(" "); //for문안에서 초기화를 시키면서  hashmap에 담기 
			    if(keyword[0].equals("Enter")) {
			    	keydata.add(keyword[1]);
			    	idMap.put(keyword[1], keyword[2]);
			    	printdata.add("en"+"님이 들어왔습니다-"+keyword[1]);
			    	//System.out.println(idMap.get(keyword[1]) + "님이 들어왔습니다-en"+keyword[1]);
			    }else if(keyword[0].equals("Leave")) { //이미 기존에 아이디가 해쉬맵에 있다는 전제하에
			    	idMap.get(keyword[1]);
			    	printdata.add("lv"+"님이 나갔습니다-"+keyword[1]);
			    	//System.out.println(idMap.get(keyword[1]) + "님이 나갔습니다-lv"+keyword[1]);
			    }else if(keyword[0].equals("Change")) { //이미 기존에 아이디가 해쉬맵에 있다는 전제하에
			    	idMap.put(keyword[1], keyword[2]);
			    	//printdata.add(idMap.get(keyword[1]) + "님이 변경됬습니다-cn"+keyword[1]);
			    	//System.out.println(idMap.get(keyword[1]) + "님이 나갔습니다-lv"+keyword[1]);
			    }
		}
	}
	
	private void EnterLeavePrint() {
		for(int i=0; i < printdata.size(); i++) {
			String nickName = idMap.get(printdata.get(i).substring(printdata.get(i).length()-7));
			if(printdata.get(i).substring(0,2).equals("en")){
				System.out.println(nickName + "들어왔습니다");
			}else if(printdata.get(i).substring(0,2).equals("lv")){
				System.out.println(nickName + "나갔습니다");
			}
			
		}

		
	}
	

}
