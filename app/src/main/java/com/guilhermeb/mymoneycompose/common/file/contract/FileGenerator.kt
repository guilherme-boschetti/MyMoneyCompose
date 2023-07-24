package com.guilhermeb.mymoneycompose.common.file.contract

import com.guilhermeb.mymoneycompose.common.file.FileContent
import java.io.File

interface FileGenerator {
    fun generateFile(fileContent: FileContent): File?
}