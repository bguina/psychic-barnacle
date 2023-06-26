package com.bguina.eurosport.test.presentation.ext

import com.github.marlonlom.utilities.timeago.TimeAgo
import java.util.Date

fun Date.timeAgo() : String = TimeAgo.using(time)
