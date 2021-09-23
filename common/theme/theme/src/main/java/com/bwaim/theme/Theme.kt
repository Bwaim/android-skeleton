package com.bwaim.theme

public enum class Theme(public val value: String) {
    LIGHT("light"),
    DARK("dark"),
    SYSTEM("system"),
    BATTERY_SAVER("battery_saver");

    public companion object {
        public fun from(value: String): Theme {
            return values().first { it.value == value }
        }
    }
}
