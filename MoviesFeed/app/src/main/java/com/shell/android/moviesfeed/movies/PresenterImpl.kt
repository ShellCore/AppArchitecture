package com.shell.android.moviesfeed.movies

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class PresenterImpl(

    private var model: MoviesMVP.Model

) : MoviesMVP.Presenter {

    companion object {
        const val TAG = "PresenterImpl"
    }

    private var view: MoviesMVP.View? = null
    private var subscription: Disposable? = null

    override fun loadData() {
        subscription = model.result()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableObserver<MoviesModel>() {
                override fun onComplete() {
                    view?.showSnackbar("Información descargada con éxito.")
                }

                override fun onNext(moviesModel: MoviesModel) {
                    view?.updateData(moviesModel)
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, e.localizedMessage!!)
                    view?.showSnackbar("Error al descargar las películas.")
                }
            })
    }

    override fun rxJavaUnsubscribe() {
        if (subscription != null && subscription!!.isDisposed) {
            subscription!!.dispose()
        }
    }

    override fun setView(view: MoviesMVP.View) {
        this.view = view
    }
}