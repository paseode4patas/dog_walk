package com.dogwalk.util;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

public class Util {

  public static String encriptarConBase64(String textoPorEncriptar) {

    String textoEncriptado = "";
    textoEncriptado = Base64.getEncoder().encodeToString(textoPorEncriptar.getBytes());

    return textoEncriptado;

  }

  public static String desencriptarConBase64(String textoPorDesencriptar) {

    byte[] bytesDesencriptado;
    String textoDesencriptado = "";

    bytesDesencriptado = Base64.getDecoder().decode(textoPorDesencriptar);
    textoDesencriptado = new String(bytesDesencriptado);

    return textoDesencriptado;

  }

  public static String generarPasswordTemporal() {

    String minuscula = "abcdefghijklmnñopqrstuvwxyz";
    String mayuscula = minuscula.toUpperCase();
    String numero = "0123456789";
    // String simbolo = "*?#$%&()=+<>";

    int longitudContrasena = 6;
    String contrasenaTemporal;

    StringBuilder contrasena = new StringBuilder(longitudContrasena);

    // 1 mayúscula
    contrasena.append(obtenerCaracteres(mayuscula, 1));

    // 3 minúsculas
    contrasena.append(obtenerCaracteres(minuscula, 3));

    // 2 números
    contrasena.append(obtenerCaracteres(numero, 2));

    // Símbolo
    // contrasena.append(obtenerCaracteres(simbolo, 1));

    contrasenaTemporal = new String(contrasena);

    return contrasenaTemporal;
  }

  private static StringBuilder obtenerCaracteres(String texto, int numeroDeCaracteres) {

    Random position = new SecureRandom();
    StringBuilder character = new StringBuilder();

    for (int i = 0; i < numeroDeCaracteres; i++) {
      character.append(texto.charAt(position.nextInt(numeroDeCaracteres)));
    }

    return character;
  }

}
