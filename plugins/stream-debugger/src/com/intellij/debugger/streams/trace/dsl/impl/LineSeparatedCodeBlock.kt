/*
 * Copyright 2000-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.debugger.streams.trace.dsl.impl

import com.intellij.debugger.streams.trace.dsl.*

/**
 * TODO: Add ability to add braces at the beginning and at the end
 *
 * @author Vitaliy.Bibaev
 */
abstract class LineSeparatedCodeBlock(statementFactory: StatementFactory, private val statementSeparator: String = "")
  : CodeBlockBase(statementFactory) {
  override fun toCode(indent: Int): String {
    if (size == 0) {
      return ""
    }

    val builder = StringBuilder()
    val statements = getStatements()
    for (convertable in statements) {
      builder.append(convertable.toCode(indent))
      if (convertable is Statement) {
        builder.append(statementSeparator)
      }
      builder.append("\n")
    }
    return builder.toString()
  }
}