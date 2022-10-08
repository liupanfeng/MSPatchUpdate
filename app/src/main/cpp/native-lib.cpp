#include <jni.h>
#include <string>



//兼容c语言
extern "C" {
//没有头文件的情况下，调用源码的方法的方式
int doPatch(int argc, char *argv[]);
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_meishe_mshotfix_MSPatchUtils_doPatch(JNIEnv *env, jclass clazz, jstring old_apk_path,
                                              jstring new_apk_path, jstring patch_path) {

    int args = 4;//参数长度
    char *argv[args];

    argv[0] = "bspatch";
    argv[1] = (char *) env->GetStringUTFChars(old_apk_path, JNI_OK);
    argv[2] = (char *) env->GetStringUTFChars(new_apk_path, JNI_OK);
    argv[3] = (char *) env->GetStringUTFChars(patch_path, JNI_OK);

    //进行补丁包合并操作
    int result = doPatch(args, argv);

    //释放资源
    env->ReleaseStringUTFChars(old_apk_path, argv[1]);
    env->ReleaseStringUTFChars(new_apk_path, argv[2]);
    env->ReleaseStringUTFChars(patch_path, argv[3]);


    return result;
}