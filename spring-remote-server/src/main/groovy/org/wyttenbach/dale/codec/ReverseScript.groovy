package org.wyttenbach.dale.codec

import groovy.util.logging.Slf4j

@Slf4j
class Reverse implements CodecService {

	String encode(String plainText) {
        log.debug("encoding '{}'", plainText)
		return plainText.reverse()
	}

	String decode(String encodedText) {
        log.debug("decoding '{}'", encodedText)
		return encodedText.reverse()
	}
}

println new Reverse().encode("hello")
