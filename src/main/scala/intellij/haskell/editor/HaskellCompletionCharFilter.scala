/*
 * Copyright 2014-2019 Rik van der Kleij
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

package intellij.haskell.editor

import com.intellij.codeInsight.lookup.CharFilter.Result
import com.intellij.codeInsight.lookup.{CharFilter, Lookup}
import intellij.haskell.HaskellFile

class HaskellCompletionCharFilter extends CharFilter {
  def acceptChar(c: Char, prefixLength: Int, lookup: Lookup): CharFilter.Result = {
    if (lookup == null || lookup.getPsiElement == null) return null
    val file = lookup.getPsiFile
    if (!file.isInstanceOf[HaskellFile]) return null

    c match {
      case ' ' |',' | ';' | ':' | '(' | ')' | '[' | ']' | '{' | '}' => Result.SELECT_ITEM_AND_FINISH_LOOKUP
      case _ => Result.ADD_TO_PREFIX
    }
  }
}