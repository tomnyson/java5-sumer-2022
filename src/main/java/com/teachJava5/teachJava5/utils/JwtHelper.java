/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teachJava5.teachJava5.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import com.teachJava5.teachJava5.domain.Account;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author tomnyson
 */
public class JwtHelper implements  Serializable {
        private static final long serialVersionUID = -2550185165626007488L;
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
        
	private String secret="Java6";
        
        // trả về username từ token
        public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
        
        public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
        
        // lấy tất cả thông tin từ token
        private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
         
        //retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
        
        //Kiểm trả xem token đã hết hạn hay chưa
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
        
        //tạo token cho user
	public String generateToken(Account account) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, account.getUsername());
	}
        
        private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
        
        //kiểm tra token
	public Boolean validateToken(String token, Account account) {
		final String username = getUsernameFromToken(token);
		return (username.equals(account.getUsername()) && !isTokenExpired(token));
	}
}
