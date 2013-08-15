package org.wyttenbach.dale.codec

import static org.wyttenbach.dale.groovy.GroovyBeanFactory.*

import org.wyttenbach.dale.codec.CodecService

CodecService codecService = getBean('codecService')

def plainText = 'Hello World'
def encodedText = codecService.encode(plainText)
println encodedText
assert plainText.equals(codecService.decode(encodedText))