-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-06-2025 a las 20:11:33
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `definitiva`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administradores`
--

CREATE TABLE `administradores` (
  `id` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  `tipo` varchar(50) DEFAULT 'AdministradorSistema',
  `activo` tinyint(1) DEFAULT 1,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `administradores`
--

INSERT INTO `administradores` (`id`, `email`, `contrasena`, `tipo`, `activo`, `fecha_creacion`) VALUES
('ADMIN_001', 'admin@hospital.com', 'admin123', 'AdministradorSistema', 1, '2025-06-10 10:41:55');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `atencion_medica`
--

CREATE TABLE `atencion_medica` (
  `id` varchar(50) NOT NULL,
  `fecha` date NOT NULL,
  `paciente_id` varchar(50) NOT NULL,
  `medico_id` int(11) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `medicacion_administrada` text DEFAULT NULL,
  `signos_vitales` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`signos_vitales`))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `citas`
--

CREATE TABLE `citas` (
  `id` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `paciente_id` varchar(50) NOT NULL,
  `medico_id` int(11) NOT NULL,
  `duracion` time DEFAULT NULL,
  `estado` varchar(50) DEFAULT 'Pendiente',
  `motivo` text DEFAULT NULL,
  `observaciones` text DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `consultas`
--

CREATE TABLE `consultas` (
  `id` varchar(50) NOT NULL,
  `fecha` date NOT NULL,
  `paciente_id` varchar(50) NOT NULL,
  `medico_id` int(11) NOT NULL,
  `motivo` text DEFAULT NULL,
  `diagnostico` text DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `examenes`
--

CREATE TABLE `examenes` (
  `id` varchar(50) NOT NULL,
  `tipo` varchar(100) NOT NULL,
  `fecha` date NOT NULL,
  `resultado` text DEFAULT NULL,
  `paciente_id` varchar(50) NOT NULL,
  `medico_solicitante_id` int(11) DEFAULT NULL,
  `estado` enum('Solicitado','En_Proceso','Completado','Cancelado') DEFAULT 'Solicitado'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial_medico`
--

CREATE TABLE `historial_medico` (
  `id` int(11) NOT NULL,
  `paciente_id` varchar(50) NOT NULL,
  `consulta` text DEFAULT NULL,
  `tipo` enum('CONSULTA','EXAMEN','OPERACION','INTERVENCION') NOT NULL,
  `fecha` date NOT NULL,
  `diagnostico` text DEFAULT NULL,
  `tratamiento` text DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `imagenes_medicas`
--

CREATE TABLE `imagenes_medicas` (
  `id` int(11) NOT NULL,
  `historial_id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `ruta` varchar(500) NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario`
--

CREATE TABLE `inventario` (
  `id` int(11) NOT NULL,
  `nombre_producto` varchar(200) NOT NULL,
  `categoria` enum('medicamentos','equipoMedico','suministros') NOT NULL,
  `cantidad_actual` int(11) DEFAULT 0,
  `cantidad_minima` int(11) DEFAULT 0,
  `precio_unitario` decimal(10,2) DEFAULT NULL,
  `fecha_vencimiento` date DEFAULT NULL,
  `proveedor` varchar(100) DEFAULT NULL,
  `ubicacion` varchar(100) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `inventario`
--

INSERT INTO `inventario` (`id`, `nombre_producto`, `categoria`, `cantidad_actual`, `cantidad_minima`, `precio_unitario`, `fecha_vencimiento`, `proveedor`, `ubicacion`, `activo`) VALUES
(1, 'Paracetamol 500mg', 'medicamentos', 100, 20, NULL, NULL, NULL, NULL, 1),
(2, 'Ibuprofeno 400mg', 'medicamentos', 75, 15, NULL, NULL, NULL, NULL, 1),
(3, 'Estetoscopio', 'equipoMedico', 5, 2, NULL, NULL, NULL, NULL, 1),
(4, 'Tensiómetro Digital', 'equipoMedico', 3, 1, NULL, NULL, NULL, NULL, 1),
(5, 'Gasas Estériles', 'suministros', 200, 50, NULL, NULL, NULL, NULL, 1),
(6, 'Jeringas Desechables', 'suministros', 500, 100, NULL, NULL, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pacientes`
--

CREATE TABLE `pacientes` (
  `id` varchar(50) NOT NULL,
  `dni` varchar(20) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `genero` enum('MASCULINO','FEMENINO','OTRO') NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telefono` int(11) DEFAULT NULL,
  `direccion` text DEFAULT NULL,
  `obra_social` varchar(100) DEFAULT NULL,
  `hospitalizado` tinyint(1) DEFAULT 0,
  `fecha_nacimiento` date DEFAULT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT current_timestamp(),
  `activo` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pacientes`
--

INSERT INTO `pacientes` (`id`, `dni`, `nombre`, `apellidos`, `genero`, `email`, `telefono`, `direccion`, `obra_social`, `hospitalizado`, `fecha_nacimiento`, `fecha_registro`, `activo`) VALUES
('PAC_1749555876780', '12345678D', 'Luisi', 'Pe', 'FEMENINO', 'lusii@mail.com', 123124, 'Calle Macedonia', '', 0, NULL, '2025-06-10 11:44:36', 1);

--
-- Disparadores `pacientes`
--
DELIMITER $$
CREATE TRIGGER `tr_paciente_id` BEFORE INSERT ON `pacientes` FOR EACH ROW BEGIN
    IF NEW.id IS NULL OR NEW.id = '' THEN
        SET NEW.id = CONCAT('PAC_', UNIX_TIMESTAMP(NOW()), '_', CONNECTION_ID());
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personal`
--

CREATE TABLE `personal` (
  `id` int(11) NOT NULL,
  `dni` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL DEFAULT '',
  `nombre` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `rol` enum('Medico','Enfermero','Administrativo','Mantenimiento','Administrador') NOT NULL,
  `turno` enum('MAÑANA','TARDE','NOCHE') NOT NULL,
  `especialidad` varchar(100) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT 1,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp(),
  `fecha_actualizacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `personal`
--

INSERT INTO `personal` (`id`, `dni`, `password`, `nombre`, `email`, `telefono`, `rol`, `turno`, `especialidad`, `activo`, `fecha_creacion`, `fecha_actualizacion`) VALUES
(1, '12345678A', 'medico123', 'Dr. Juan Pérez', 'juan.perez@hospital.com', '123456789', 'Medico', 'MAÑANA', 'Cardiología', 1, '2025-06-10 10:41:55', '2025-06-10 10:41:55'),
(2, '87654321B', 'enfermero123', 'Ana García', 'ana.garcia@hospital.com', '987654321', 'Enfermero', 'TARDE', NULL, 1, '2025-06-10 10:41:55', '2025-06-10 10:41:55'),
(3, '11223344C', 'admin123', 'Luis Martín', 'luis.martin@hospital.com', '111222333', 'Administrativo', 'MAÑANA', NULL, 1, '2025-06-10 10:41:55', '2025-06-10 10:41:55'),
(4, '44332211D', 'mant123', 'Carlos López', 'carlos.lopez@hospital.com', '444333222', 'Mantenimiento', 'NOCHE', NULL, 1, '2025-06-10 10:41:55', '2025-06-10 10:41:55'),
(5, '11223344A', '1234', 'Esmeralda', 'esme@mail.com', '234567890', 'Administrador', 'MAÑANA', NULL, 1, '2025-06-10 11:42:53', '2025-06-10 11:42:53'),
(6, '12345678N', '', 'Peter', 'peter@mail.com', '000111222', 'Mantenimiento', 'NOCHE', NULL, 1, '2025-06-10 16:55:00', '2025-06-10 16:55:00');

--
-- Disparadores `personal`
--
DELIMITER $$
CREATE TRIGGER `tr_personal_update` BEFORE UPDATE ON `personal` FOR EACH ROW BEGIN
    SET NEW.fecha_actualizacion = CURRENT_TIMESTAMP;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recetas`
--

CREATE TABLE `recetas` (
  `id` varchar(50) NOT NULL,
  `medicamento` varchar(200) NOT NULL,
  `dosis` varchar(100) DEFAULT NULL,
  `frecuencia` varchar(100) DEFAULT NULL,
  `fecha` date NOT NULL,
  `paciente_id` varchar(50) NOT NULL,
  `medico_id` int(11) NOT NULL,
  `activa` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salas`
--

CREATE TABLE `salas` (
  `id` varchar(50) NOT NULL,
  `tipo` enum('HABITACION','QUIROFANO','CONSULTORIO') NOT NULL,
  `numero` int(11) NOT NULL,
  `disponible` tinyint(1) DEFAULT 1,
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `salas`
--

INSERT INTO `salas` (`id`, `tipo`, `numero`, `disponible`, `fecha_creacion`) VALUES
('CONS_001', 'CONSULTORIO', 1, 1, '0000-00-00 00:00:00'),
('CONS_002', 'CONSULTORIO', 2, 1, '0000-00-00 00:00:00'),
('HAB_001', 'HABITACION', 1, 1, '0000-00-00 00:00:00'),
('HAB_002', 'HABITACION', 2, 1, '0000-00-00 00:00:00'),
('QUIR4', 'QUIROFANO', 44, 0, '2025-06-10 17:54:24'),
('QUIR_001', 'QUIROFANO', 11, 1, '0000-00-00 00:00:00'),
('SALA', 'HABITACION', 8, 1, '2025-06-10 17:58:18');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_infraestructura`
--

CREATE TABLE `tipos_infraestructura` (
  `id` int(11) NOT NULL,
  `nombre` enum('HABITACION','QUIROFANO','CONSULTORIO') NOT NULL,
  `descripcion` text DEFAULT NULL,
  `activo` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipos_infraestructura`
--

INSERT INTO `tipos_infraestructura` (`id`, `nombre`, `descripcion`, `activo`) VALUES
(1, 'HABITACION', 'Habitación para pacientes', 1),
(2, 'QUIROFANO', 'Sala de operaciones quirúrgicas', 1),
(3, 'CONSULTORIO', 'Sala de consulta médica', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `turnos_programados`
--

CREATE TABLE `turnos_programados` (
  `id` int(11) NOT NULL,
  `personal_id` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `turno` enum('MAÑANA','TARDE','NOCHE') NOT NULL,
  `activo` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vista_citas_completas`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `vista_citas_completas` (
`id` int(11)
,`fecha` date
,`duracion` time
,`estado` varchar(50)
,`paciente_nombre` varchar(100)
,`paciente_apellidos` varchar(100)
,`paciente_dni` varchar(20)
,`medico_nombre` varchar(100)
,`medico_especialidad` varchar(100)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vista_historial_completo`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `vista_historial_completo` (
`id` int(11)
,`consulta` text
,`tipo` enum('CONSULTA','EXAMEN','OPERACION','INTERVENCION')
,`fecha` date
,`diagnostico` text
,`tratamiento` text
,`paciente_nombre` varchar(100)
,`paciente_apellidos` varchar(100)
,`paciente_dni` varchar(20)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vista_inventario_alertas`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `vista_inventario_alertas` (
`id` int(11)
,`nombre_producto` varchar(200)
,`categoria` enum('medicamentos','equipoMedico','suministros')
,`cantidad_actual` int(11)
,`cantidad_minima` int(11)
,`estado_stock` varchar(7)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vista_personal_completo`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `vista_personal_completo` (
`id` int(11)
,`dni` varchar(20)
,`nombre` varchar(100)
,`email` varchar(100)
,`telefono` varchar(20)
,`rol` enum('Medico','Enfermero','Administrativo','Mantenimiento','Administrador')
,`turno` enum('MAÑANA','TARDE','NOCHE')
,`especialidad` varchar(100)
,`activo` tinyint(1)
);

-- --------------------------------------------------------

--
-- Estructura para la vista `vista_citas_completas`
--
DROP TABLE IF EXISTS `vista_citas_completas`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vista_citas_completas`  AS SELECT `c`.`id` AS `id`, `c`.`fecha` AS `fecha`, `c`.`duracion` AS `duracion`, `c`.`estado` AS `estado`, `pac`.`nombre` AS `paciente_nombre`, `pac`.`apellidos` AS `paciente_apellidos`, `pac`.`dni` AS `paciente_dni`, `med`.`nombre` AS `medico_nombre`, `med`.`especialidad` AS `medico_especialidad` FROM ((`citas` `c` join `pacientes` `pac` on(`c`.`paciente_id` = `pac`.`id`)) join `personal` `med` on(`c`.`medico_id` = `med`.`id`)) WHERE `pac`.`activo` = 1 AND `med`.`activo` = 1 ;

-- --------------------------------------------------------

--
-- Estructura para la vista `vista_historial_completo`
--
DROP TABLE IF EXISTS `vista_historial_completo`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vista_historial_completo`  AS SELECT `hm`.`id` AS `id`, `hm`.`consulta` AS `consulta`, `hm`.`tipo` AS `tipo`, `hm`.`fecha` AS `fecha`, `hm`.`diagnostico` AS `diagnostico`, `hm`.`tratamiento` AS `tratamiento`, `pac`.`nombre` AS `paciente_nombre`, `pac`.`apellidos` AS `paciente_apellidos`, `pac`.`dni` AS `paciente_dni` FROM (`historial_medico` `hm` join `pacientes` `pac` on(`hm`.`paciente_id` = `pac`.`id`)) WHERE `pac`.`activo` = 1 ;

-- --------------------------------------------------------

--
-- Estructura para la vista `vista_inventario_alertas`
--
DROP TABLE IF EXISTS `vista_inventario_alertas`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vista_inventario_alertas`  AS SELECT `i`.`id` AS `id`, `i`.`nombre_producto` AS `nombre_producto`, `i`.`categoria` AS `categoria`, `i`.`cantidad_actual` AS `cantidad_actual`, `i`.`cantidad_minima` AS `cantidad_minima`, CASE WHEN `i`.`cantidad_actual` <= `i`.`cantidad_minima` THEN 'CRÍTICO' WHEN `i`.`cantidad_actual` <= `i`.`cantidad_minima` * 1.5 THEN 'BAJO' ELSE 'NORMAL' END AS `estado_stock` FROM `inventario` AS `i` WHERE `i`.`activo` = 1 ;

-- --------------------------------------------------------

--
-- Estructura para la vista `vista_personal_completo`
--
DROP TABLE IF EXISTS `vista_personal_completo`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vista_personal_completo`  AS SELECT `p`.`id` AS `id`, `p`.`dni` AS `dni`, `p`.`nombre` AS `nombre`, `p`.`email` AS `email`, `p`.`telefono` AS `telefono`, `p`.`rol` AS `rol`, `p`.`turno` AS `turno`, `p`.`especialidad` AS `especialidad`, `p`.`activo` AS `activo` FROM `personal` AS `p` WHERE `p`.`activo` = 1 ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administradores`
--
ALTER TABLE `administradores`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indices de la tabla `atencion_medica`
--
ALTER TABLE `atencion_medica`
  ADD PRIMARY KEY (`id`),
  ADD KEY `medico_id` (`medico_id`),
  ADD KEY `idx_fecha_atencion` (`fecha`),
  ADD KEY `idx_paciente_atencion` (`paciente_id`);

--
-- Indices de la tabla `citas`
--
ALTER TABLE `citas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `paciente_id` (`paciente_id`),
  ADD KEY `idx_fecha_cita` (`fecha`),
  ADD KEY `idx_medico_fecha` (`medico_id`,`fecha`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `consultas`
--
ALTER TABLE `consultas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_fecha_consulta` (`fecha`),
  ADD KEY `idx_paciente` (`paciente_id`),
  ADD KEY `idx_medico` (`medico_id`);

--
-- Indices de la tabla `examenes`
--
ALTER TABLE `examenes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `medico_solicitante_id` (`medico_solicitante_id`),
  ADD KEY `idx_paciente_examen` (`paciente_id`),
  ADD KEY `idx_tipo_examen` (`tipo`),
  ADD KEY `idx_estado_examen` (`estado`);

--
-- Indices de la tabla `historial_medico`
--
ALTER TABLE `historial_medico`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_paciente_fecha` (`paciente_id`,`fecha`),
  ADD KEY `idx_tipo` (`tipo`);

--
-- Indices de la tabla `imagenes_medicas`
--
ALTER TABLE `imagenes_medicas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_historial` (`historial_id`);

--
-- Indices de la tabla `inventario`
--
ALTER TABLE `inventario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_categoria` (`categoria`),
  ADD KEY `idx_vencimiento` (`fecha_vencimiento`),
  ADD KEY `idx_cantidad` (`cantidad_actual`,`cantidad_minima`);

--
-- Indices de la tabla `pacientes`
--
ALTER TABLE `pacientes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `dni` (`dni`),
  ADD KEY `idx_dni_paciente` (`dni`),
  ADD KEY `idx_nombre_paciente` (`nombre`,`apellidos`),
  ADD KEY `idx_pacientes_hospitalizado` (`hospitalizado`);

--
-- Indices de la tabla `personal`
--
ALTER TABLE `personal`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `dni` (`dni`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `idx_dni` (`dni`),
  ADD KEY `idx_email` (`email`),
  ADD KEY `idx_rol` (`rol`),
  ADD KEY `idx_turno` (`turno`),
  ADD KEY `idx_personal_turno_activo` (`turno`,`activo`);

--
-- Indices de la tabla `recetas`
--
ALTER TABLE `recetas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_paciente_receta` (`paciente_id`),
  ADD KEY `idx_medico_receta` (`medico_id`),
  ADD KEY `idx_fecha_receta` (`fecha`),
  ADD KEY `idx_recetas_activa` (`activa`);

--
-- Indices de la tabla `salas`
--
ALTER TABLE `salas`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_tipo_numero` (`tipo`,`numero`),
  ADD KEY `idx_tipo_disponible` (`tipo`,`disponible`),
  ADD KEY `idx_infraestructura_disponible` (`disponible`);

--
-- Indices de la tabla `tipos_infraestructura`
--
ALTER TABLE `tipos_infraestructura`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `turnos_programados`
--
ALTER TABLE `turnos_programados`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_personal_fecha` (`personal_id`,`fecha`),
  ADD KEY `idx_fecha_turno` (`fecha`,`turno`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `citas`
--
ALTER TABLE `citas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `historial_medico`
--
ALTER TABLE `historial_medico`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `imagenes_medicas`
--
ALTER TABLE `imagenes_medicas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `inventario`
--
ALTER TABLE `inventario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `personal`
--
ALTER TABLE `personal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `tipos_infraestructura`
--
ALTER TABLE `tipos_infraestructura`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `turnos_programados`
--
ALTER TABLE `turnos_programados`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `atencion_medica`
--
ALTER TABLE `atencion_medica`
  ADD CONSTRAINT `atencion_medica_ibfk_1` FOREIGN KEY (`paciente_id`) REFERENCES `pacientes` (`id`),
  ADD CONSTRAINT `atencion_medica_ibfk_2` FOREIGN KEY (`medico_id`) REFERENCES `personal` (`id`);

--
-- Filtros para la tabla `citas`
--
ALTER TABLE `citas`
  ADD CONSTRAINT `citas_ibfk_1` FOREIGN KEY (`paciente_id`) REFERENCES `pacientes` (`id`),
  ADD CONSTRAINT `citas_ibfk_2` FOREIGN KEY (`medico_id`) REFERENCES `personal` (`id`);

--
-- Filtros para la tabla `consultas`
--
ALTER TABLE `consultas`
  ADD CONSTRAINT `consultas_ibfk_1` FOREIGN KEY (`paciente_id`) REFERENCES `pacientes` (`id`),
  ADD CONSTRAINT `consultas_ibfk_2` FOREIGN KEY (`medico_id`) REFERENCES `personal` (`id`);

--
-- Filtros para la tabla `examenes`
--
ALTER TABLE `examenes`
  ADD CONSTRAINT `examenes_ibfk_1` FOREIGN KEY (`paciente_id`) REFERENCES `pacientes` (`id`),
  ADD CONSTRAINT `examenes_ibfk_2` FOREIGN KEY (`medico_solicitante_id`) REFERENCES `personal` (`id`);

--
-- Filtros para la tabla `historial_medico`
--
ALTER TABLE `historial_medico`
  ADD CONSTRAINT `historial_medico_ibfk_1` FOREIGN KEY (`paciente_id`) REFERENCES `pacientes` (`id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `imagenes_medicas`
--
ALTER TABLE `imagenes_medicas`
  ADD CONSTRAINT `imagenes_medicas_ibfk_1` FOREIGN KEY (`historial_id`) REFERENCES `historial_medico` (`id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `recetas`
--
ALTER TABLE `recetas`
  ADD CONSTRAINT `recetas_ibfk_1` FOREIGN KEY (`paciente_id`) REFERENCES `pacientes` (`id`),
  ADD CONSTRAINT `recetas_ibfk_2` FOREIGN KEY (`medico_id`) REFERENCES `personal` (`id`);

--
-- Filtros para la tabla `turnos_programados`
--
ALTER TABLE `turnos_programados`
  ADD CONSTRAINT `turnos_programados_ibfk_1` FOREIGN KEY (`personal_id`) REFERENCES `personal` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
