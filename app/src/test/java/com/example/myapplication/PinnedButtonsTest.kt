package com.example.myapplication

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class PinnedButtonsTest {
    @Test
    fun `list1`() {
        val a = PinnedButtons::restimg
        assertThat(a).isNotNull()
    }

    @Test
    fun `list of flexs`() {
        val a = PinnedButtons::elemnt
        assertThat(a).isNotNull()
    }
    @Test
    fun `adding users`() {
        val a = Regestration::addUser
        assertThat(a).isNotSameInstanceAs(Person_menu::addUser)
    }
    @Test
    fun ` saved rests`() {
        val a = Saved_menu::show_restourant
        assertThat(a).isNotSameInstanceAs(Find_menu::show_restourant)
    }
    @Test
    fun ` saved rests 2`() {
        val a = Saved_menu::show_restourant
        assertThat(a).isNotSameInstanceAs(Order::show_restourant)
    }
    @Test
    fun ` saved rests 3`() {
        val a = Saved_menu::add_elements
        assertThat(a).isNotSameInstanceAs(Order::add_elements)
    }
    @Test
    fun `  rests `() {
        val a = Restaurant_menu::addRest
        assertThat(a).isNotSameInstanceAs(Order::addRest)
    }
    @Test
    fun `  rests 2 `() {
        val a = Restaurant_menu::addaboutrest
        assertThat(a).isNotSameInstanceAs(Order::addRest)
    }
}