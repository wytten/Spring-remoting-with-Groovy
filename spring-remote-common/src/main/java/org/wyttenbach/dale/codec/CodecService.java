package org.wyttenbach.dale.codec;

public interface CodecService {
  String encode(String plainText);
  String decode(String encodedText);
}