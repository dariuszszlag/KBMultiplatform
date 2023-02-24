package com.dariusz.kbembed

import android.content.Context

object KBEmbedBuilder : KBEmbed {

    override fun openKB(context: Context) = context.startActivity(MainActivity.newIntent(context))

}