/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class it_marcom_swtedge_nat_NativeEdge */

#ifndef _Included_it_marcom_swtedge_nat_NativeEdge
#define _Included_it_marcom_swtedge_nat_NativeEdge
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     it_marcom_swtedge_nat_NativeEdge
 * Method:    webview_create
 * Signature: (IJ)J
 */
JNIEXPORT jlong JNICALL Java_it_marcom_swtedge_nat_NativeEdge_create
  (JNIEnv *, jclass, jint, jlong);

/*
 * Class:     it_marcom_swtedge_nat_NativeEdge
 * Method:    webview_destroy
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_it_marcom_swtedge_nat_NativeEdge_destroy
  (JNIEnv *, jclass, jlong);

/*
 * Class:     it_marcom_swtedge_nat_NativeEdge
 * Method:    webview_run
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_it_marcom_swtedge_nat_NativeEdge_run
  (JNIEnv *, jclass, jlong);

/*
 * Class:     it_marcom_swtedge_nat_NativeEdge
 * Method:    webview_terminate
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_it_marcom_swtedge_nat_NativeEdge_terminate
  (JNIEnv *, jclass, jlong);

/*
 * Class:     it_marcom_swtedge_nat_NativeEdge
 * Method:    webview_dispatch
 * Signature: (JLjava/lang/Runnable;J)V
 */
JNIEXPORT void JNICALL Java_it_marcom_swtedge_nat_NativeEdge_dispatch
  (JNIEnv *, jclass, jlong, jobject, jlong);

/*
 * Class:     it_marcom_swtedge_nat_NativeEdge
 * Method:    webview_get_window
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_it_marcom_swtedge_nat_NativeEdge_getWindow
  (JNIEnv *, jclass, jlong);

/*
 * Class:     it_marcom_swtedge_nat_NativeEdge
 * Method:    webview_set_title
 * Signature: (JLjava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_it_marcom_swtedge_nat_NativeEdge_setTitle
  (JNIEnv *, jclass, jlong, jstring);

/*
 * Class:     it_marcom_swtedge_nat_NativeEdge
 * Method:    webview_set_bounds
 * Signature: (JIIIII)V
 */
JNIEXPORT void JNICALL Java_it_marcom_swtedge_nat_NativeEdge_setBounds
  (JNIEnv *, jclass, jlong, jint, jint, jint, jint, jint);

/*
 * Class:     it_marcom_swtedge_nat_NativeEdge
 * Method:    webview_navigate
 * Signature: (JLjava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_it_marcom_swtedge_nat_NativeEdge_navigate
  (JNIEnv *, jclass, jlong, jstring);

/*
 * Class:     it_marcom_swtedge_nat_NativeEdge
 * Method:    webview_init
 * Signature: (JLjava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_it_marcom_swtedge_nat_NativeEdge_init
  (JNIEnv *, jclass, jlong, jstring);

/*
 * Class:     it_marcom_swtedge_nat_NativeEdge
 * Method:    webview_eval
 * Signature: (JLjava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_it_marcom_swtedge_nat_NativeEdge_eval(
    JNIEnv *, jclass, jlong, jstring, jobject);

/*
 * Class:     it_marcom_swtedge_nat_NativeEdge
 * Method:    webview_bind
 * Signature: (JLjava/lang/String;Lca/weblite/webview/WebViewNativeCallback;J)V
 */
JNIEXPORT void JNICALL Java_it_marcom_swtedge_nat_NativeEdge_bind
  (JNIEnv *, jclass, jlong, jstring, jobject, jlong);



#ifdef __cplusplus
}
#endif
#endif