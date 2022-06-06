package ba.etf.rma22.projekat.data.models

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    //vraca sva istrazivanja
    @GET("/istrazivanje")
    suspend fun dajSvaIstrazivanja(
        @Query("offset") offset: Int
    ): Response<List<Istrazivanje>>

    //vraca istrazivanje sa id-om
    @GET("/istrazivanje/{id}")
    suspend fun dajIstrazivanje(
        @Path("id") istrazivanjeId: Int
    ): Response<Istrazivanje>

    //vraca istrazivanje sa grupe sa datim idom
    @GET("/grupa/{gid}/istrazivanje")
    suspend fun dajIstrazivanjeSaGrupe(
        @Path("gid") grupaId: Int
    ): Response<List<Istrazivanje>>

    //vraca grupe u kojima je anketa dostupna
    @GET("/anketa/{id}/grupa")
    suspend fun dajGrupeZaAnketu(
        @Path("id") anketaId: Int
    ): Response<List<Grupa>>

    //dodaje studenta sa hash-om id u grupu sa id-em gid
    @POST("/grupa/{gid}/student/{id}")
    suspend fun dodajStudentaUGrupu(
        @Path("id") studentId: String,
        @Path("gid") grupaId: Int
    ): Response<ServisPoruka>

    //vraca grupe u koje je student upisan
    @GET("/student/{id}/grupa")
    suspend fun dajGrupeZaStudenta(
        @Path("id") studentId: String
    ): Response<List<Grupa>>

    //vraca sve grupe
    @GET("/grupa")
    suspend fun dajSveGrupe(): Response<List<Grupa>>

    //vraca grupu sa zadanim id-em
    @GET("/grupa/{id}")
    suspend fun dajGrupu(
        @Path("id") grupaId: Int
    ): Response<Grupa>

    //daj sve ankete
    @GET("/anketa")
    suspend fun dajSveAnkete(
        @Query("offset") offset: Int
    ): Response<List<Anketa>>
    // dal je ovdje offset query?

    //daj anketu sa id-om
    @GET("/anketa/{id}")
    suspend fun dajAnketu(
        @Path("id") anketaId: Int
    ): Response<Anketa>

    //vraca ankete koje su dodijeljene ovoj grupi
    @GET("/grupa/{id}/ankete")
    suspend fun dajAnketeZaGrupu(
        @Path("id") grupaId: Int
    ): Response<List<Anketa>>

    //lista sa odgovorima
    @GET("student/{id}/anketataken/{ktid}/odgovori")
    suspend fun dajOdgovoreZaPokusaj(
        @Path("id") studentId: Int,
        @Path("ktid") pokusajRjesavanjaAnkete: Int
    ): Response<List<Odgovor>>

    //dodaje odgovor
    @POST("/student/{id}/anketataken/{ktid}/odgovor")
    suspend fun postaviOdgovorZaPokusaj(
        @Path("id") studentId: Int,
        @Path("ktid") pokusajRjesavanjaAnkete: Int
    ): Response<Odgovor> //?? zasto odgovor kao response, dal gore treba body request odgovora

    //vraca listu pokusaja za ankete studenta
    @GET("/student/{id}/anketataken")
    suspend fun dajSvePokusaje(
        @Path("id") studentId: String
    ): Response<List<AnketaTaken>>

    //zapocni odgovaranje studenta sa id-em id na anketu sa id-em kid
    @POST("/student/{id}/anketa/{kid}")
    suspend fun zapocniAnketu(
        @Path("id") studentId: Int,
        @Path("kid") anketaId: Int
    ): Response<AnketaTaken>

    //daj pitanja sa ankete
    @GET("/anketa/{id}/pitanja")
    suspend fun dajPitanjaZaAnketu(
        @Path("id") anketaId: Int
    ): Response<List<Pitanje>>
}