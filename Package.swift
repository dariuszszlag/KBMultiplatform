// swift-tools-version:5.3
import PackageDescription

let version = "0.1.20"

let xcFrameworkUrl = "https://maven.pkg.github.com/dariuszszlag/KBMultiplatform/com/dariusz/kbcore-kmmbridge/\(String(describing: version))/kbcore-kmmbridge-\(String(describing: version)).zip"

let checksumUrl = "https://maven.pkg.github.com/dariuszszlag/KBMultiplatform/com/dariusz/kbcore-kmmbridge/\(String(describing: version))/kbcore-kmmbridge-\(String(describing: version)).zip.sha256"

let checksumString = "2a03bf7625a165d4796b507747aa777bdf577cf70b041433fae51b1ea3e1606b"

let packageName = "kbcore"

let package = Package(
    name: packageName,
    platforms: [
        .iOS(.v16)
    ],
    products: [
        .library(
            name: packageName,
            targets: [packageName]
        ),
    ],
    targets: [
        .binaryTarget(
            name: packageName,
            url: xcFrameworkUrl,
            checksum: checksumString
        )
        ,
    ]
)