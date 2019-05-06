/*
 * Copyright 2019 the JSON-P Test Suite Authors.
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
package org.leadpony.jsonp.testsuite;

import java.util.Comparator;

import javax.json.stream.JsonLocation;

/**
 * A utility class for {@link JsonLocation}.
 *
 * @author leadpony
 */
class JsonLocations {

    static final Comparator<JsonLocation> COMPARATOR = new Comparator<JsonLocation>() {

        @Override
        public int compare(JsonLocation o1, JsonLocation o2) {
            int result = compareLong(o1.getLineNumber(), o2.getLineNumber());
            if (result == 0) {
                result = compareLong(o1.getColumnNumber(), o2.getColumnNumber());
                if (result == 0) {
                    result = compareLong(o1.getStreamOffset(), o2.getStreamOffset());
                }
            }
            return result;
        }
    };

    private static int compareLong(long a, long b) {
        if (a < b) {
            return -1;
        } else if (a > b) {
            return 1;
        } else {
            return 0;
        }
    }

    static JsonLocation at(long lineNumber, long columnNumber, long streamOffset) {
        return new JsonLocationImpl(lineNumber, columnNumber, streamOffset);
    }

    private static class JsonLocationImpl implements JsonLocation {

        private final long lineNmber;
        private final long columnNumber;
        private final long streamOffset;

        JsonLocationImpl(long lineNumber, long columnNumber, long streamOffset) {
            this.lineNmber = lineNumber;
            this.columnNumber = columnNumber;
            this.streamOffset = streamOffset;
        }

        @Override
        public long getLineNumber() {
            return lineNmber;
        }

        @Override
        public long getColumnNumber() {
            return columnNumber;
        }

        @Override
        public long getStreamOffset() {
            return streamOffset;
        }

        @Override
        public String toString() {
            StringBuilder b = new StringBuilder("[");
            b.append(getLineNumber()).append(',');
            b.append(getColumnNumber()).append(',');
            b.append(getStreamOffset());
            return b.append(']').toString();
        }
    }
}
