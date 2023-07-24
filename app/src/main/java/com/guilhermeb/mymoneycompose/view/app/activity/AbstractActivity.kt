package com.guilhermeb.mymoneycompose.view.app.activity

import androidx.activity.ComponentActivity
import com.guilhermeb.mymoneycompose.R
import com.guilhermeb.mymoneycompose.common.component.CustomProgressDialog
import com.guilhermeb.mymoneycompose.view.ui.components.DefaultActionBarInterface

abstract class AbstractActivity : ComponentActivity(), DefaultActionBarInterface {

    protected val progressDialog by lazy {
        CustomProgressDialog(this, R.string.loading, true)
    }

    override fun actionBack() {
        finish()
    }
}