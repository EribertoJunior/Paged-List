package com.example.listapaginada.di

import com.example.listapaginada.MainViewModel
import com.example.listapaginada.data.datasource.PokemonRemoteMediator
import com.example.listapaginada.data.datasource.local.AppDataBase
import com.example.listapaginada.data.datasource.local.dao.PokemonDao
import com.example.listapaginada.data.datasource.local.dao.RemoteKeysDao
import com.example.listapaginada.data.datasource.remote.RetrofitConfig
import com.example.listapaginada.data.datasource.remote.service.ListPokemonService
import com.example.listapaginada.data.repository.ListPokemonRepository
import com.example.listapaginada.data.repository.ListPokemonRepositoryImpl
import com.example.listapaginada.domain.usecase.GetPokemonListUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    single { AppDataBase.getDatabase(context = androidContext()) }
    factory<PokemonDao> { get<AppDataBase>().pokemonDao() }
    factory<RemoteKeysDao> { get<AppDataBase>().remoteKeysDao() }

    single { RetrofitConfig.instance }
    single<ListPokemonService> { get<Retrofit>().create(ListPokemonService::class.java) }

    factory { PokemonRemoteMediator(listPokemonService = get(), pokemonDao = get(), remoteKeysDao = get()) }

    single<ListPokemonRepository> { ListPokemonRepositoryImpl(pokemonRemoteMediator = get(), pokemonDao = get()) }

    factory { GetPokemonListUseCase(listPokemonRepository = get()) }

    viewModel { MainViewModel(getPokemonListUseCase = get()) }

}