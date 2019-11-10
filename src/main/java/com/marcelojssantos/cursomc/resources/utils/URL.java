package com.marcelojssantos.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	public static String decodeParam(String s){
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static List<Integer> decodeIntList(String s){
		//quebra a string recebida, pelo ',' e guarda no vetor de String 'vet'
		String[] vet = s.split(",");
		
		//cria uma lista de Integer
		List<Integer> list = new ArrayList<>();

		//percorre o vetor de String baseado no seu tamanho
		for(int i=0; i<vet.length; i++){
			//converte o pedaço de string em um Integer
			list.add(Integer.parseInt(vet[i]));	
		}
		
		return list;
		
		/*
		 * substitui todo o código anterior
		 * return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).
		 * 		 				collect(Collectors.toList());
		 */
	}
}
