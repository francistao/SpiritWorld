#ifdef GL_ES
precision highp float;

// Our own uniform as passed in by onSetShaderMaterialConstants (value is in range [0;1])
uniform highp float myValue; // must use same precision as in fragment shader, else we get linker errors
#else
uniform float myValue;
#endif

// Predefined uniform and attribute names. For an exhaustive list of available uniforms
// and vertex attributes, check the online documentation!
uniform mat4 metaio_mat4_modelViewProjection;

// Note: Our 3D model does not have vertex colors, so no need to consider them here
attribute vec4 inVertex; // position in object space
attribute vec2 inTexCoord; // UV coordinates

varying vec2 outTexCoord;
varying float outTransparency;

void main()
{
	#define M_PI 3.14159265358979323846

	// Warp the vertex a bit in X and Z direction to create a "wobbling" effect
	const float FREQ = 40.0;
	const float VINFLUENCE = 10.0;
	const float SININFLUENCE = 0.8;
	vec3 warpedVertex = inVertex.xyz + SININFLUENCE*vec3(sin(VINFLUENCE*inVertex.x + myValue * M_PI * FREQ), 0.0, sin(VINFLUENCE*inVertex.z + myValue * M_PI * FREQ));

	outTransparency = pow(myValue, 0.8);

	outTexCoord = inTexCoord;
	gl_Position = metaio_mat4_modelViewProjection * mix(inVertex, vec4(warpedVertex, 1), 1.0 - myValue);
}