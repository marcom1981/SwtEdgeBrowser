// +build !windows
#include <iostream>
#define WEBVIEW_IMPLEMENTATION
#include "webview_jni.h"
#include "webview.h"

#ifdef __cplusplus
extern "C" {
#endif

typedef void (*callback_t)(const char *, void *);

WEBVIEW_API void webview_bind(webview_t w, const char *name, callback_t fn,
                              void *arg) {
  webview::webview *wv = (webview::webview *)w;

  wv->bind(name, [w, fn](std::string s) -> std::string {
    if (fn != NULL) {
      fn(s.c_str(), (void *)w);
    }
    //
    return "";
  });
}

/*
 * Class:     it_marcom_swtedge_nat_NativeEdge
 * Method:    create
 * Signature: (IJ)J
 */
							
JNIEXPORT jlong JNICALL Java_it_marcom_swtedge_nat_NativeEdge_create(
    JNIEnv *env, jclass clazz, jint debug, jlong win) {
  return (jlong)webview_create((int)debug, (void *)win);
}

/*
 * Class:     it_marcom_swtedge_nat_NativeEdge
 * Method:    destroy
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_it_marcom_swtedge_nat_NativeEdge_destroy(
    JNIEnv *env, jclass clazz, jlong wv) {
  webview_destroy((webview_t)wv);
}

/*
 * Class:     it_marcom_swtedge_nat_NativeEdge
 * Method:    run
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_it_marcom_swtedge_nat_NativeEdge_run(
    JNIEnv *env, jclass clazz, jlong wv) {
  webview_run((webview_t)wv);
}

/*
 * Class:     it_marcom_swtedge_nat_NativeEdge
 * Method:    terminate
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_it_marcom_swtedge_nat_NativeEdge_terminate(
    JNIEnv *env, jclass clazz, jlong wv) {
  webview_terminate((webview_t)wv);
}

/*
 * Class:     it_marcom_swtedge_nat_NativeEdge
 * Method:    dispatch
 * Signature: (JLjava/lang/Runnable;J)V
 */
JNIEXPORT void JNICALL Java_it_marcom_swtedge_nat_NativeEdge_dispatch(
    JNIEnv *env, jclass clazz, jlong w, jobject callback, jlong arg) {
  webview::webview *wv = (webview::webview *)w;

  JavaVM *jvm;
  (env)->GetJavaVM(&jvm);
  jclass objClass = env->GetObjectClass(callback);
  jclass callbacksClass;
  if (objClass) {
    callbacksClass = reinterpret_cast<jclass>(env->NewGlobalRef(objClass));
    env->DeleteLocalRef(objClass);
  }
  jobject callbackInstance = env->NewGlobalRef(callback);

  wv->dispatch([env, callback, jvm, callbacksClass, callbackInstance]() {
    (jvm)->AttachCurrentThread((void **)&env, NULL);
    jmethodID run = env->GetMethodID(callbacksClass, "run", "()V");
    (env)->CallVoidMethod(callbackInstance, run);
    env->DeleteGlobalRef(callbackInstance);
    env->DeleteGlobalRef(callbacksClass);
    (jvm)->DetachCurrentThread();
  });
}

/*
 * Class:     it_marcom_swtedge_nat_NativeEdge
 * Method:    getWindow
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL
Java_it_marcom_swtedge_nat_NativeEdge_getWindow(JNIEnv *env,
                                                           jclass clazz,
                                                           jlong w) {
  return (jlong)webview_get_window((webview_t)w);
}

/*
 * Class:     it_marcom_swtedge_nat_NativeEdge
 * Method:    setTitle
 * Signature: (JLjava/lang/String;)V
 */
JNIEXPORT void JNICALL
Java_it_marcom_swtedge_nat_NativeEdge_set_Title(JNIEnv *env,
                                                          jclass clazz, jlong w,
                                                          jstring title) {
  const char *utf = (env)->GetStringUTFChars(title, 0);
  webview_set_title((webview_t)w, utf);
  (env)->ReleaseStringUTFChars(title, utf);
}

/*
 * Class:     it_marcom_swtedge_nat_NativeEdge
 * Method:    setBounds
 * Signature: (JIIIII)V
 */
JNIEXPORT void JNICALL
Java_it_marcom_swtedge_nat_NativeEdge_setBounds(JNIEnv *env,
                                                           jclass clazz,
                                                           jlong wv, jint x,
                                                           jint y, jint w,
                                                           jint h, jint flags) {
  //cout << "Setting size" << w << ", " << h << std::flush;
  webview_set_size((webview_t)wv, w, h, flags);
}

/*
 * Class:     it_marcom_swtedge_nat_NativeEdge
 * Method:    navigate
 */
JNIEXPORT void JNICALL Java_it_marcom_swtedge_nat_NativeEdge_navigate(
    JNIEnv *env, jclass clazz, jlong w, jstring url) {
  const char *utf = (env)->GetStringUTFChars(url, 0);
  webview_navigate((webview_t)w, utf);
  (env)->ReleaseStringUTFChars(url, utf);
}

/*
 * Class:     it_marcom_swtedge_nat_NativeEdge
 * Method:    init
 */
JNIEXPORT void JNICALL Java_it_marcom_swtedge_nat_NativeEdge_init(
    JNIEnv *env, jclass clazz, jlong w, jstring js) {
  const char *utf = (env)->GetStringUTFChars(js, 0);
  webview_init((webview_t)w, utf);
  (env)->ReleaseStringUTFChars(js, utf);
}

/*
 * Class:     it_marcom_swtedge_nat_NativeEdge
 * Method:    eval
 */
JNIEXPORT void JNICALL Java_it_marcom_swtedge_nat_NativeEdge_eval(
    JNIEnv *env, jclass clazz, jlong w, jstring js, jobject fn) {
  webview::webview *wv = (webview::webview *)w;
  const char *utf = (env)->GetStringUTFChars(js, 0);
  if (fn != nullptr) {
    JavaVM *jvm;
    (env)->GetJavaVM(&jvm);
    jobject fnInstance = env->NewGlobalRef(fn);
    jclass objClass = env->GetObjectClass(fn);
    jclass callbacksClass;
    if (objClass) {
      callbacksClass = reinterpret_cast<jclass>(env->NewGlobalRef(objClass));
      env->DeleteLocalRef(objClass);
    }

    wv->eval(utf,
             [env, jvm, fnInstance, callbacksClass](int status,
                                                    std::string s) -> void {
               (jvm)->AttachCurrentThread((void **)&env, NULL);
               jmethodID invoke = (env)->GetMethodID(callbacksClass, "invoke",
                                                     "(ILjava/lang/String;)V");
               (env)->CallVoidMethod(fnInstance, invoke, status,
                                     (env)->NewStringUTF(s.c_str()));
               (jvm)->DetachCurrentThread();
             });
  } else {
    wv->eval(utf, nullptr);
  }

  (env)->ReleaseStringUTFChars(js, utf);
}

/*
 * Class:     it_marcom_swtedge_nat_NativeEdge
 * Method:    bind
 */
JNIEXPORT void JNICALL Java_it_marcom_swtedge_nat_NativeEdge_bind(
    JNIEnv *env, jclass clazz, jlong wv, jstring name, jobject fn, jlong arg) {
  webview::webview *w = (webview::webview *)wv;
  const char *nameUtf = (env)->GetStringUTFChars(name, 0);
  JavaVM *jvm;
  (env)->GetJavaVM(&jvm);
  jobject fnInstance = env->NewGlobalRef(fn);
  jclass objClass = env->GetObjectClass(fn);
  jclass callbacksClass;
  if (objClass) {
    callbacksClass = reinterpret_cast<jclass>(env->NewGlobalRef(objClass));
    env->DeleteLocalRef(objClass);
  }
  w->bind(nameUtf,
          [fnInstance, env, jvm, callbacksClass, w,
           nameUtf](std::string s) -> std::string {
            (jvm)->AttachCurrentThread((void **)&env, NULL);
            const char *strArg = s.c_str();

            jmethodID invoke =
                (env)->GetMethodID(callbacksClass, "invoke",
                                   "(Ljava/lang/String;J)Ljava/lang/String;");

            jobject strReturn = (env)->CallObjectMethod(
                fnInstance, invoke, (env)->NewStringUTF(strArg));
            if (strReturn != nullptr) {
              const char *strUtfReturn =
                  (env)->GetStringUTFChars((jstring)strReturn, 0);
              std::string str(strUtfReturn);
              (env)->ReleaseStringUTFChars((jstring)strReturn, strUtfReturn);
              //env->DeleteGlobalRef(fnInstance);
              (jvm)->DetachCurrentThread();
              return str;
            } else {
               std::string str((const char*)"null");
               w->eval("window._rpc[" + std::string(nameUtf) + "].resolve(" +
                           str + "); window._rpc[" + std::string(nameUtf) +
                           "] = undefined",
                       nullptr);
               return str;
                            
            }
          });
  (env)->ReleaseStringUTFChars(name, nameUtf);
}

#ifdef __cplusplus
}
#endif
