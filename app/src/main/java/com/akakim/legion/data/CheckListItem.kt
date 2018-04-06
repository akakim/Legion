package com.akakim.legion.data

/**
 * Created by RyoRyeong Kim on 2018-04-06.
 *
 * 내가 대본을 보고 난 뒤 무엇을 해야할치 체크하는 리스트이다.
 * TODO : 사용자가 만들기 나름
 * TODO : 기본 설정도 만들어 놓자.
 */


data class CheckListItem(
        var content : String ,
        var isChecked : Boolean
)