package com.akakim.soundlibrary

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-06-13
 * CheapSoundFile is
 */


class CheapSoundFile{
    open interface ProgressListener{

        /**
         * CheapSoundfile
         */
        fun reportProgress( fractionComplete : Double )
    }

    open interface Factory{
        fun create() : CheapSoundFile
        fun getSupportedExtensions() : Array<String>
    }


}