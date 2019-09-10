package com.shell.android.daggerlogin

import com.shell.android.daggerlogin.login.LoginActivityMVP
import com.shell.android.daggerlogin.login.LoginActivityPresenter
import com.shell.android.daggerlogin.login.User
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class PresenterUnitTest {

    var presenter: LoginActivityPresenter? = null
    var user: User? = null

    var mocketModel: LoginActivityMVP.Model? = null
    var mocketView: LoginActivityMVP.View? = null

    @Before
    fun initialization() {
        mocketModel = mock(LoginActivityMVP.Model::class.java)
        mocketView = mock(LoginActivityMVP.View::class.java)

        user = User(name = "Luz", lastname = "Arely")

//        `when`(mocketModel!!.getUser()).thenReturn(user)
//        `when`(mocketView!!.getName()).thenReturn("Cesar")
//        `when`(mocketView!!.getLastname()).thenReturn("Morales")

        presenter = LoginActivityPresenter(mocketModel!!)
        presenter!!.setView(mocketView!!)
    }

    @Test
    fun noExistsInteractionWithView() {
        presenter!!.getCurrentUser()
        // Falla porque siempre hay interacción con view
        //verifyZeroInteractions(mocketView)
        verify(mocketView, times(1))?.showUserNotAvailable()
    }

    @Test
    fun loadUserFromRepoWhenValidUserIsPresent() {
        `when`(mocketModel!!.getUser()).thenReturn(user)

        presenter!!.getCurrentUser()

        // Comprobamos la interactuación con el modelo de datos
        verify(mocketModel, times(1))?.getUser()

        // Comprobamos la interactuación con la vista
        verify(mocketView, times(1))?.setName("Luz")
        verify(mocketView, times(1))?.setLastname("Arely")
        verify(mocketView, never())?.showUserNotAvailable()

    }
}