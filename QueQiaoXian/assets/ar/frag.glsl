#ifdef GL_ES
precision mediump float;
uniform highp float myValue; // must use same precision as in vertex shader
#else
uniform float myValue;
#endif

uniform sampler2D metaio_tex_color;

varying vec2 outTexCoord;
varying float outTransparency;

void main()
{
	// Important: We're using "Premultiplied Alpha" (glBlendFunc(GL_ONE, GL_ONE_MINUS_SRC_ALPHA))
	// as blending technique, which has multiple advantages but needs some more thinking when
	// developing fragment shaders. You have to ensure that the RGB values of the output color
	// (gl_FragColor) are premultiplied with the intended alpha value. Whenever you want to change
	// the transparency, you will also need to multiply the RGB values (see last line of this
	// shader for an example).
	// For more information on this technique, please read this great article:
	// http://home.comcast.net/~tom_forsyth/blog.wiki.html#[[Premultiplied%20alpha]]

	vec4 texColor = texture2D(metaio_tex_color, outTexCoord);
	texColor = vec4(texColor.rgb * texColor.a, texColor.a);

	#define M_PI_2 1.57079632679489661923

	// Blend against white (but consider alpha transparency of the original texture)
	gl_FragColor.rgba = mix(texColor.rgba, vec4(texColor.a), pow(cos(M_PI_2 * myValue), 2.0));

	// Add fading
	gl_FragColor.rgba *= outTransparency;
}