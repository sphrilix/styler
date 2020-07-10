/*
 * Copyright 2018 Paul Schaub.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package name.neuhalfen.projects.crypto.bouncycastle.openpgp.keys.generation;

import java.util.HashSet;
import java.util.Set;
import org.bouncycastle.bcpg.sig.KeyFlags;

/**
 * Wraps bouncy castles org.bouncycastle.bcpg.sig.KeyFlags into an enum.
 *
 * @see KeyFlags
 */
public enum KeyFlag {

  CERTIFY_OTHER(KeyFlags.CERTIFY_OTHER),
  SIGN_DATA(KeyFlags.SIGN_DATA),
  ENCRYPT_COMMS(KeyFlags.ENCRYPT_COMMS),
  ENCRYPT_STORAGE(KeyFlags.ENCRYPT_STORAGE),
  SPLIT(KeyFlags.SPLIT),
  AUTHENTICATION(KeyFlags.AUTHENTICATION),
  SHARED(KeyFlags.SHARED),
  ;

  private final int flag;

  KeyFlag(int flag) {
    this.flag = flag;
  }

  public int getFlag() {
    return flag;
  }

  public static Set<KeyFlag> fromInteger(int bitmask) {
    final Set<KeyFlag> flags = new HashSet<>();
    int identifiedFlags = 0;

    for (final KeyFlag f : KeyFlag.values()) {
      if ((bitmask & f.flag) != 0) {
        flags.add(f);
        identifiedFlags |= f.flag;
      }
    }

    if (identifiedFlags != bitmask) {
      final int unknownFlags = ~identifiedFlags & bitmask;
      throw new IllegalArgumentException(
          "Could not identify the following KeyFlags: 0b" + Long.toBinaryString(unknownFlags));
    }
    return flags;
  }
}
