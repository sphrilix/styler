/*
 * Copyright 2015 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package java.io;

/**
 * Provides a series of utilities to be reused between IO classes.
 *
 * <p>
 * TODO(chehayeb): move these checks to InternalPreconditions.
 * </p>
 */
@SuppressWarnings("checkstyle:abbreviationaswordinname")
final class IOUtils {

  /**
   * Validates the offset and the byte count for the given array of bytes.
   *
   * @param buffer Array of bytes to be checked.
   * @param byteOffset Starting offset in the array.
   * @param byteCount Total number of bytes to be accessed.
   * @throws NullPointerException if the given reference to the buffer is null.
   * @throws IndexOutOfBoundsException if {@code byteOffset} is negative, {@code byteCount} is
   *         negative or their sum exceeds the buffer length.
   */
  public static void checkOffsetAndCount(final byte[] buffer, final int byteOffset,
      final int byteCount) {
    // Ensure we throw a NullPointerException instead of a JavascriptException in case the
    // given buffer is null.
    if (buffer == null) {
      throw new NullPointerException();
    }
    checkOffsetAndCount(buffer.length, byteOffset, byteCount);
  }

  /**
   * Validates the offset and the byte count for the given array of characters.
   *
   * @param buffer Array of characters to be checked.
   * @param charOffset Starting offset in the array.
   * @param charCount Total number of characters to be accessed.
   * @throws NullPointerException if the given reference to the buffer is null.
   * @throws IndexOutOfBoundsException if {@code charOffset} is negative, {@code charCount} is
   *         negative or their sum exceeds the buffer length.
   */
  public static void checkOffsetAndCount(final char[] buffer, final int charOffset,
      final int charCount) {
    // Ensure we throw a NullPointerException instead of a JavascriptException in case the
    // given buffer is null.
    if (buffer == null) {
      throw new NullPointerException();
    }
    checkOffsetAndCount(buffer.length, charOffset, charCount);
  }

  /**
   * Validates the offset and the byte count for the given array length.
   *
   * @param length Length of the array to be checked.
   * @param offset Starting offset in the array.
   * @param count Total number of elements to be accessed.
   * @throws IndexOutOfBoundsException if {@code offset} is negative, {@code count} is negative or
   *         their sum exceeds the given {@code length}.
   */
  private static void checkOffsetAndCount(final int length, final int offset, final int count) {
    if (offset < 0 || count < 0 || offset + count > length) {
      throw new IndexOutOfBoundsException();
    }
  }

  private IOUtils() {}
}
