package com.github.dudgns0507.domain.usecase

data class JsonUseCases(
    val getPostUseCase: GetPostUseCase,
    val getPostsUseCase: GetPostsUseCase,
    val getPostsUseCaseEx1: GetPostsUseCaseEx1,
    val getPostsUseCaseEx2: GetPostsUseCaseEx2,
    val getPostsUseCaseEx3: GetPostsUseCaseEx3,
    val getCommentsUseCase: GetCommentsUseCase,
    val patchPostUseCase: PatchPostUseCase,
    val deletePostUseCase: DeletePostUseCase
)